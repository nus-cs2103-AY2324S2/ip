import java.util.ArrayList;
public class Duke {
    private static final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE,
    }

    private static final Ui ui = new Ui();
    private ArrayList<Task> list;
    private Storage storage;

    Duke() {
        this.list = new ArrayList<>();
        this.storage = new Storage();
        try {
            this.storage.loadData(this.list);
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
    }

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

    public static void main(String[] args) {
        Duke duke = new Duke();
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
                        duke.storage.saveData(duke.list);
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
