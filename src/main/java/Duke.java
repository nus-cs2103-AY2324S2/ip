import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;

public class Duke {
    private static final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Ui ui = new Ui();
    private ArrayList<Task> list = new ArrayList<>();

    private static enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE,
    }

    private static final String filePath = Paths.get("data", "duke.txt").toString();
    private File dataFile = new File(filePath);

    private void add(Task task) {
        this.list.add(task);
        ui.showAddedTask(task, this.list.size());
    }

    private void list() {
        ui.showList(this.list);
    }

    private void mark(String[] input) throws IncompleteCommandException, InvalidArgumentException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        try {
            int index = Integer.parseInt(input[1]) - 1;
            Task task = this.list.get(index);
            task.markAsDone();
            ui.showMarkedTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException();
        }
    }

    private void unmark(String[] input) throws IncompleteCommandException, InvalidArgumentException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        try {
            int index = Integer.parseInt(input[1]) - 1;
            Task task = this.list.get(index);
            task.markNotDone();
            ui.showUnmarkedTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException();
        }
    }

    private void addTodo(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        String description = input[1];
        this.add(new Todo(description));
    }

    private void addDeadline(String[] input) throws IncompleteCommandException, NoDeadlineException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        input = input[1].split(" /by ", 2);
        if (input.length == 1) {
            throw new NoDeadlineException();
        }
        String description = input[0];
        String by = input[1];
        this.add(new Deadline(description, by));
    }

    private void addEvent(String[] input) throws IncompleteCommandException, NoPeriodException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        input = input[1].split(" /from ", 2);
        if (input.length == 1) {
            throw new NoPeriodException();
        }
        String description = input[0];
        String[] arr = input[1].split(" /to ", 2);
        if (arr.length == 1) {
            throw new NoPeriodException();
        }
        this.add(new Event(description, arr[0], arr[1]));
    }

    private void delete(String[] input) throws IncompleteCommandException, InvalidArgumentException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        } 
        try {
            int index = Integer.parseInt(input[1]) - 1;
            Task task = this.list.get(index);
            this.list.remove(index);
            ui.showDeletedTask(task, this.list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException();
        }
    }

    private void createDataFile() {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdir();
            }
            this.dataFile.createNewFile();
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
    }

    private void loadData() {
        if (!this.dataFile.exists()) {
            createDataFile();
            return;
        }
        try {
            Scanner sc = new Scanner(this.dataFile);
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(" \\| ");
                String type = input[0];
                boolean isDone = input[1].equals("1");
                String description = input[2];
                switch (type) {
                    case "T":
                        this.list.add(new Todo(description, isDone));
                        break;
                    case "D":
                        this.list.add(new Deadline(description, input[3], isDone));
                        break;
                    case "E":
                        this.list.add(new Event(description, input[3], input[4], isDone));
                        break;
                }
            }
            sc.close();  
        } catch (Exception e) {
            this.dataFile.delete();
            this.list.clear();
            ui.showCorruptedData();
        }
      
    }

    private void saveData() {
        try {
            java.io.FileWriter fw = new java.io.FileWriter(this.dataFile);
            for (Task task : this.list) {
                String type = "";
                String description = task.getDescription();
                String isDone = task.getIsDone() ? "1" : "0";
                String by = "";
                String start = "";
                String end = "";
                if (task instanceof Todo) {
                    type = "T";
                    fw.write(type + " | " + isDone + " | " + description + "\n");
                } else if (task instanceof Deadline) {
                    type = "D";
                    by = ((Deadline) task).getBy();
                    fw.write(type + " | " + isDone + " | " + description + " | " + by + "\n");
                } else if (task instanceof Event) {
                    type = "E";
                    start = ((Event) task).getStart();
                    end = ((Event) task).getEnd();
                    fw.write(type + " | " + isDone + " | " + description + " | " + start + " | " + end + "\n");
                }
            }
            fw.close();
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.loadData();
        ui.sayGreetings();

        while (true) {
            try {
                String userInput = ui.readCommand();
                String[] input = userInput.split(" ", 2);
                String action = input[0].toUpperCase();
                ui.showLine();
                switch (Command.valueOf(action)) {
                    case BYE:
                        ui.sayBye();
                        duke.saveData();
                        return;
                    case LIST:
                        duke.list();
                        break;
                    case MARK:
                        duke.mark(input);
                        break;
                    case UNMARK:
                        duke.unmark(input);
                        break;
                    case TODO:
                        duke.addTodo(input);
                        break;
                    case DEADLINE:
                        duke.addDeadline(input);
                        break;
                    case EVENT:
                        duke.addEvent(input);
                        break;
                    case DELETE:
                        duke.delete(input);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (IllegalArgumentException e) {
                if (e.getMessage().contains("No enum constant Duke.Command.")) {
                    ui.showErrorMessage(new UnknownCommandException());
                } else {
                    ui.showErrorMessage(e);
                }
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
    }
}
