import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.load());
        } catch (EmisException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (EmisException e) {
                ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
        Ui.exit();
    }

    public static void main(String[] args) {
        new Duke("./data/emis.txt").run();
    }

    // Task class
    public static class Task {
        protected String description;
        protected boolean isDone;
    
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public Task(boolean isDone, String description) {
            this.description = description;
            this.isDone = isDone;
        }
    
        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }
    
        public void markAsDone() {
            this.isDone = true;
            Ui.showLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t[" + this.getStatusIcon() + "] " + this.description);
            Ui.showLine();
        }
    
        public void markAsUndone() {
            this.isDone = false;
            Ui.showLine();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t[" + this.getStatusIcon() + "] " + this.description);
            Ui.showLine();
        }

        public String storeString() {
            // done = 1, not done = 0
            int status = isDone ? 1 : 0;
            return status + " | " + this.description;
        }
    
        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    // Deadline class
    public static class Deadline extends Task {
        protected String by;
        protected LocalDateTime doByDateTime;
    
        public Deadline(String description, String by) {
            super(description);
            this.by = by.trim();
            this.doByDateTime = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        public Deadline(boolean isDone, String description, String by) {
            super(isDone, description);
            this.by = by;
            this.doByDateTime = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        @Override
        public String storeString() {
            return "D | " + super.storeString() + " | " + this.by;
        }
    
        @Override
        public String toString() {
            return"[D]" + super.toString() + " (by: " + this.doByDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
            //this.doByDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
        }
    }

    // ToDo class
    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        public ToDo(boolean isDone, String description) {
            super(isDone, description);
        }

        @Override
        public String storeString() {
            return "T | " + super.storeString();
        }
    
        @Override
        public String toString() {
            return"[T]" + super.toString();
        }
    }

    // Event class
    public static class Event extends Task {
        protected String from;
        protected String to;
    
        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        public Event(boolean isDone, String description, String from, String to) {
            super(isDone, description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String storeString() {
            return "E | " + super.storeString() + " | " + this.from + " | " + this.to;
        }
    
        @Override
        public String toString() {
            return"[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
        }
    }



    
    // Ui class, deals with interactions with the user
    public static class Ui {
        private static Scanner sc;
        Ui () {
            sc = new Scanner(System.in);
        }

        public void showWelcome() {
            showLine();
            System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
            showLine();
        }

        public static void showLine() {
            System.out.println("\t____________________________________________________________");
        }

        public String readCommand() {
            return sc.nextLine().trim();
        }

        public void showLoadingError() {
            showLine();
            System.out.println("\t There was an error loading the file.");
            showLine();
        }

        public void showError(String str) {
            System.out.println(str);
        }

        public void showHelp() {
            showLine();
            System.out.println("\tEmis is happy to help with printing a list of tasks with the command 'list'.");
            System.out.println("\tAdd todos with the command 'todo (insert task here)'.");
            System.out.println("\tAdd deadlines with the command 'deadline (insert deadline name) /by (insert deadline here in the format yyyy-mm-dd hhmm)'.");
            System.out.println("\tAdd events with the command 'event (insert event name) /from (insert start time) /to (insert end time)'.");
            System.out.println("\tMark tasks as done with the command 'mark (task no)'.");
            System.out.println("\tMark tasks as undone with the command 'unmark (task no)'.");
            System.out.println("\tDelete tasks  with the command 'delete (task no)'.");
            System.out.println("\tTo stop talking to Emis, please say 'bye'.");
            showLine();
        }

        public static void exit() {
            sc.close();
            showLine();
            System.out.println("\tBye. Hope to see you again soon!");
            showLine();
            System.exit(0);
        }
    }





    // Storage class, deals with loading tasks and saving tasks
    public static class Storage {
        private String filePath;
        private ArrayList<Task> al;

        Storage(String filePath) {
            this.filePath = filePath;
            this.al = new ArrayList<>();
        }

        public ArrayList<Task> load() throws EmisException {
            try {
                Scanner sT = new Scanner(new File(this.filePath));
                while (sT.hasNextLine()) {
                    // read from file, add to arraylist al
                    String input = sT.nextLine();
                    String[] data = input.split("\\|");
                    String taskType = data[0].trim();
                    String retrieveStatus = data[1].trim();
                    boolean convertStatus = retrieveStatus.equals("1");
                    String retrieveDescription = data[2].trim();
    
                    if (taskType.equals("T")) {
                        this.al.add(new ToDo(convertStatus, retrieveDescription));
    
                    } else if (taskType.equals("D")) {
                        String finishBy = data[3].trim();
                        this.al.add(new Deadline(convertStatus, retrieveDescription, finishBy));
    
                    } else if (taskType.equals("E")) {
                        String startFrom = data[3].trim();
                        String endBy = data[4].trim();
                        this.al.add(new Event(convertStatus, retrieveDescription, startFrom, endBy));
    
                    } else {
                        throw new EmisException("Invalid input type.");
                    }
                } 
                sT.close();
                
            } catch (FileNotFoundException FNFe) {
                // file does not exist yet, so create
                try {
                    File emisTxt = new File(this.filePath);
                    emisTxt.getParentFile().mkdirs();
                    if (emisTxt.createNewFile()) {
                        System.out.println("File created");
                    } else {
                        System.out.println("File already exists");
                    }
                } catch (IOException IOe) {
                    System.out.println("An error occurred while creating the file.");
                }
                /*
                catch () {
                    // handle folder-does-not-exist-yet case
                    // handle data file being corrupted (i.e. content not in expected format)
                }
                */
            }
            return this.al;
        }

        public void updateStorage() {
            try {
                FileWriter fw = new FileWriter(this.filePath);
                String s = "";
                for (int i = 0; i < this.al.size(); i++) {
                    s += this.al.get(i).storeString();
                    s += "\n";
                }
                fw.write(s);
                fw.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException IOe) {
                System.out.println("Error occurred while writing to the file.");
                IOe.printStackTrace();
            }
        }
    }
    


    
    // Parser class, deals with making sense of the user command
    public static class Parser {
        public static Command parse(String fullCommand) throws EmisException {
            Integer spaceIndex = fullCommand.indexOf(" ");

            if (spaceIndex == -1) {
                // no spaces found
                // commands are either "bye", "list", or invalid command
                if (fullCommand.equals("bye")) {
                    return new ExitCommand();
                } else if (fullCommand.equals("list")) {
                    return new PrintCommand();
                } else {
                    throw new EmisException("Invalid command.");
                }
            } else {
                // spaces found
                // possible commands are "todo", "deadline", "event", "mark", "unmark", "delete"
                // otherwise invalid
                String action = fullCommand.substring(0, spaceIndex);
                if (action.equals("todo")) {
                    String description = fullCommand.substring(spaceIndex + 1);
                    return new ToDoCommand(description);

                } else if (action.equals("deadline")) {
                    String deadline = fullCommand.substring(spaceIndex + 1);
                    int slashIndex = deadline.indexOf("/by");
                    String description = deadline.substring(0, slashIndex);
                    String by = deadline.substring(slashIndex + 3);
                    return new DeadlineCommand(description, by);

                } else if (action.equals("event")) {
                    String event = fullCommand.substring(spaceIndex + 1);
                    int slashIndexFrom = event.indexOf("/from");
                    int slashIndexTo = event.indexOf("/to");
                    String description = event.substring(0, slashIndexFrom);
                    String from = event.substring(slashIndexFrom + 5, slashIndexTo);
                    String to = event.substring(slashIndexTo + 3);
                    return new EventCommand(description, from, to);

                } else if (action.equals("mark")) {
                    Integer taskNo = Integer.valueOf(fullCommand.substring(spaceIndex + 1).trim());
                    return new MarkCommand(taskNo);

                } else if (action.equals("unmark")) {
                    Integer taskNo = Integer.valueOf(fullCommand.substring(spaceIndex + 1).trim());
                    return new UnmarkCommand(taskNo);

                } else if (action.equals("delete")) {
                    Integer taskNo = Integer.valueOf(fullCommand.substring(spaceIndex + 1).trim());
                    return new DeleteCommand(taskNo);

                } else {
                    // invalid command
                    throw new EmisException("Invalid command!");
                }
            }
        }
    }




    // TaskList class, contains task list and its operations
    public static class TaskList {
        private ArrayList<Task> al;

        TaskList(ArrayList<Task> al) {
            this.al = al;
        }

        TaskList() {
            this.al = new ArrayList<>();
        }

        public void printList() {
            Ui.showLine();
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < this.al.size(); i++) {
                System.out.println("\t\t" + (i + 1) + "." + this.al.get(i).toString());
            }
            Ui.showLine();
        }

        public void deleteTask(int taskNo) throws EmisException {
            if (taskNo <= 0 || taskNo > al.size()) {
                throw new EmisException("This task does not exist!");
            } else {
                Ui.showLine();
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t\t" + this.al.get(taskNo - 1).toString());
                this.al.remove(taskNo - 1);
                System.out.println("\tNow you have " + this.al.size() + " tasks in the list.");
                Ui.showLine();
            }
        }

        public void addTask(Task task) {
            this.al.add(task);
            Ui.showLine();
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t\t" + task.toString());
            System.out.println("\tNow you have " + this.al.size() + " tasks in the list.");
            Ui.showLine();
        }

        public void markAsDone(int taskNo) throws EmisException {
            if (taskNo <= 0 || taskNo > al.size()) {
                throw new EmisException("This task does not exist!");
            } else {
                Task t = this.al.get(taskNo - 1);
                t.markAsDone();
                this.al.set(taskNo - 1, t);
            }
        }

        public void markAsUndone(int taskNo) throws EmisException {
            if (taskNo <= 0 || taskNo > al.size()) {
                throw new EmisException("This task does not exist!");
            } else {
                Task t = this.al.get(taskNo - 1);
                t.markAsUndone();
                this.al.set(taskNo - 1, t);
            }
        }
    }



    public static class EmisException extends Exception {
        EmisException(String str) {
            super(str);
        }
    }

    public static abstract class Command {
        public abstract void execute(TaskList t, Ui ui, Storage s);
        public abstract boolean isExit();
    }

    public static class ToDoCommand extends Command {
        private String description;

        ToDoCommand(String description) {
            this.description = description;
        }

        @Override
        public void execute(TaskList t, Ui ui, Storage s) {
            t.addTask(new ToDo(this.description));
            s.updateStorage();
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class DeadlineCommand extends Command {
        private String description;
        private String by;

        DeadlineCommand(String d, String b) {
            this.description = d;
            this.by = b;
        }

        @Override
        public void execute(TaskList t, Ui ui, Storage s) {
            t.addTask(new Deadline(this.description, this.by));
            s.updateStorage();
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class EventCommand extends Command {
        private String description;
        private String from;
        private String to;

        EventCommand(String d, String f, String t) {
            this.description = d;
            this.from = f;
            this.to = t;
        }

        @Override
        public void execute(TaskList t, Ui ui, Storage s) {
            t.addTask(new Event(this.description, this.from, this.to));
            s.updateStorage();
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class MarkCommand extends Command {
        private int taskNo;

        MarkCommand(int taskNo) {
            this.taskNo = taskNo;
        }

        @Override
        public void execute(TaskList t, Ui ui, Storage s) {
            try {
                t.markAsDone(this.taskNo);
                s.updateStorage();
            } catch (EmisException e) {
                ui.showError(e.getMessage());
            }
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class UnmarkCommand extends Command {
        private int taskNo;

        UnmarkCommand(int taskNo) {
            this.taskNo = taskNo;
        }

        @Override
        public void execute(TaskList t, Ui ui, Storage s) {
            try {
                t.markAsUndone(this.taskNo);
                s.updateStorage();
            } catch (EmisException e) {
                ui.showError(e.getMessage());
            }
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class DeleteCommand extends Command {
        private int taskNo;

        DeleteCommand(int taskNo) {
            this.taskNo = taskNo;
        }

        @Override
        public void execute(TaskList t, Ui ui, Storage s) {
            try {
                t.deleteTask(this.taskNo);
                s.updateStorage();
            } catch (EmisException e) {
                ui.showError(e.getMessage());
            }
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class PrintCommand extends Command {
        PrintCommand() {
        }

        @Override
        public void execute(TaskList t, Ui ui, Storage s) {
            t.printList();
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }

    public static class ExitCommand extends Command {
        ExitCommand() {
        }

        @Override
        public void execute(TaskList t, Ui ui, Storage s) {
            Ui.exit();
        }

        @Override
        public boolean isExit() {
            return true;
        }
    }
}