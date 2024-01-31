import java.util.Scanner;

public class Duke {
    private static final int INDEX_START = 1;
    private final String horizontalLine = "____________________________________________________________\n";
    private final String name;
    private final TaskList tasks;
    private final Storage storage;
    private static final String FILEPATH = "./data/duke.txt";

    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public Duke(String name) {
        this.name = name;
        this.storage = new Storage(FILEPATH);
        this.tasks = new TaskList(storage.loadTasks());
    }

    public void greetUser() {
        System.out.println(this.horizontalLine
                + "Hello! I'm "
                + this.name
                + "\n"
                + "What can I do for you?\n"
                + this.horizontalLine);
    }

    public void exit() {
        System.out.println(" Bye. Hope to see you again soon!\n" + this.horizontalLine);
    }

    // Process commands
    public void echo() {
        Scanner myScannerObj = new Scanner(System.in);

        while (myScannerObj.hasNext()) {
            System.out.print(this.horizontalLine);
            String msg = myScannerObj.nextLine();

            try {
                if (msg.equalsIgnoreCase(Command.BYE.name())) {
                    this.exit();
                    break;
                } else if (msg.equalsIgnoreCase(Command.LIST.name())) {
                    this.toDoList();
                } else if (msg.toUpperCase().startsWith(Command.MARK.name()) || (msg.toUpperCase().startsWith(Command.UNMARK.name()))){
                    this.markToDo(msg);
                } else if (msg.toUpperCase().startsWith(Command.DELETE.name())) {
                    this.deleteToDo(msg);
                } else {
                    this.addToDo(msg);
                }
            } catch (DukeException e) {
                System.out.println(" Error: " + e.getMessage());
            }

            System.out.print(this.horizontalLine + "\n");
        }

        myScannerObj.close();

    }

    private void addToDo(String msg) throws DukeException{
        Task t;

        if (msg.toUpperCase().startsWith(Command.TODO.name())) {
            t = todo(msg.substring(Command.TODO.name().length()).trim());
        } else if (msg.toUpperCase().startsWith(Command.DEADLINE.name())) {
            t = dueDate(msg.substring(Command.DEADLINE.name().length()).trim());
        } else if ( msg.toUpperCase().startsWith(Command.EVENT.name())) {
            t = schedule(msg.substring(Command.EVENT.name().length()).trim());
        } else {
            throw new DukeException("Invalid format. Please use 'todo', 'deadline', or 'event'.");
        }

        tasks.addTask(t);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + tasks.getSize() + " tasks in the list.");
        tasks.saveTask(storage);
    }

    private void deleteToDo(String msg) throws DukeException{
        String[] part = msg.split(" ");

        if (part.length == 2) {
            String index = part[1].trim();

            try {
                int taskIndex = Integer.parseInt(index) - INDEX_START;
                if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                    Task t = this.tasks.deleteTask(taskIndex);

                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.getSize() + " tasks in the list.");
                    tasks.saveTask(storage);
                } else {
                    throw new DukeException("Task not found.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format. Please use integers only.");
            }
        } else {
            throw new DukeException("Invalid format. Please use 'delete <index>'.");
        }
    }

    private void toDoList() {
        if (tasks.isEmpty()) {
            System.out.println(" There are no tasks in the list.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            tasks.printList(INDEX_START);
        }
    }

    private void markToDo(String msg) throws DukeException{
        String[] part = msg.split(" ");

        if (part.length == 2) {
            String command = part[0].trim().toUpperCase();
            String index = part[1].trim();

            try {
                int taskIndex = Integer.parseInt(index) - INDEX_START;
                if (taskIndex >= 0 && taskIndex < tasks.getSize()) {
                    Task t = this.tasks.getTasks(taskIndex);
                    boolean isDone = command.equals(Command.MARK.name());
                    t.setDone(isDone);
                    System.out.println(isDone ? " Nice! I've marked this task as done:" :
                            " OK, I've marked this task as not done yet:");
                    System.out.println("   " + t);
                    tasks.saveTask(storage);
                } else {
                    throw new DukeException("Task not found.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format. Please use integers only.");
            }

        } else {
            throw new DukeException("Invalid format. Please use 'mark <index>' or 'unmark <index>'.");
        }
    }

    private Deadline dueDate(String command) throws DukeException{
        int byIndex = command.indexOf(" /by ");
        if ( byIndex != 0 && byIndex != -1) {
            String description = command.substring(0, byIndex).trim();
            String by = command.substring(byIndex + 4).trim();
            return new Deadline(description, by);
        } else {
            throw new DukeException("Invalid format. Please use 'deadline <description> /by <datetime>'.");
        }
    }

    private Event schedule(String command) throws DukeException {
        int byIndex = command.indexOf(" /from ");
        if (byIndex != 0 && byIndex != -1) {
            String description = command.substring(0, byIndex).trim();
            String fromTo = command.substring(byIndex + 6).trim();
            String[] part = fromTo.split(" /to ", 2);
            if (part.length == 2) {
                String from = part[0].trim();
                String to = part[1].trim();
                return new Event(description, from, to);
            }
        }
        throw new DukeException("Invalid format. Please use 'event <description> /from <datetime> /to <datetime>'.");
    }

    private Todo todo(String command) throws DukeException{
        String description = command.trim();

        if (command.isEmpty()) {
            throw new DukeException("Invalid format. Please use 'todo <description>'.");
        }

        return new Todo(description);
    }

    public static void main(String[] args) {

        Duke zenifyBot = new Duke("Zenify");
        zenifyBot.greetUser();
        zenifyBot.echo();
    }
}