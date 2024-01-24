import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int INDEX_START = 1;
    private final String horizontalLine = "____________________________________________________________\n";
    private final String name;

    private final ArrayList<Task> tasks;

    public Duke(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
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
        System.out.println("Bye. Hope to see you again soon!\n" + this.horizontalLine);
    }

    //process commands
    public void echo() {
        Scanner myScannerObj = new Scanner(System.in);
        System.out.print("Message Zenify: ");
        while (myScannerObj.hasNext()) {
            System.out.print(this.horizontalLine);
            String msg = myScannerObj.nextLine();
            if (msg.equalsIgnoreCase("bye")) {
                this.exit();
                break;
            } else if (msg.equalsIgnoreCase("list")) {
                this.toDoList();
            } else if (msg.toUpperCase().startsWith("MARK")){
                this.markToDo(msg, true);
            } else if (msg.toUpperCase().startsWith("UNMARK")) {
                this.markToDo(msg, false);
            } else {
                this.addToDo(msg);
            }
            System.out.print(this.horizontalLine);
            System.out.print("\nMessage Zenify: ");
        }

        myScannerObj.close();

    }

    private void addToDo(String msg) {
        Task t;

        if (msg.toUpperCase().startsWith("TODO")) {
            t = todo(msg.substring(4).trim());
        } else if (msg.toUpperCase().startsWith("DEADLINE")) {
            t = dueDate(msg.substring(8).trim());
        } else if ( msg.toUpperCase().startsWith("EVENT")) {
            t = schedule(msg.substring(5).trim());
        } else {
            System.out.println("Invalid task format. Please use 'todo', 'deadline', or 'event'.");
            return;
        }

        if (t == null) {
            return;
        }

        tasks.add(t);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + t);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");

    }

    private void toDoList() {
        if (tasks.isEmpty()) {
            System.out.println(" Please add task!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            int taskCount = INDEX_START;
            for (Task task : tasks) {
                System.out.println(" " + taskCount + "." + task);
                taskCount++;
            }
        }
    }

    private void markToDo(String command, boolean isDone) {
        try {
            int taskIndex = Integer.parseInt(command.split(" ")[1]) - INDEX_START;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task t = this.tasks.get(taskIndex);
                t.setDone(isDone);
                System.out.println(isDone ? " Nice! I've marked this task as done:" :
                        " OK, I've marked this task as not done yet:");
                System.out.println("   " + t);
            } else {
                System.out.println("Task not found: Index is out of bounds.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(" Invalid command format.");
        }
    }

    private Deadline dueDate(String command) {
        int byIndex = command.indexOf(" /by ");
        if ( byIndex != 0 && byIndex != -1) {
            String description = command.substring(0, byIndex).trim();
            String by = command.substring(byIndex + 4).trim();
            return new Deadline(description, by);
        } else {
            System.out.println("Invalid format. Please use 'deadline <description> /by <datetime>'.");
            return null;
        }
    }

    private Event schedule(String command) {
        int byIndex = command.indexOf(" /from ");
        if ( byIndex != 0 && byIndex != -1) {
            String description = command.substring(0, byIndex).trim();
            String fromTo = command.substring(byIndex + 6).trim();
            String[] part = fromTo.split(" /to ", 2);
            if (part.length == 2) {
                String from = part[0].trim();
                String to = part[1].trim();
                return new Event(description, from, to);
            }
        }
        System.out.println("Invalid format. Please use 'event <description> /from <datetime> /to <datetime>'.");
        return null;
    }

    private Todo todo(String command) {

        String description = command.trim();

        if (command.isEmpty()) {
            System.out.println("Invalid format Please use 'todo <description>'.");
            return null;
        }

        return new Todo(description);
    }

    public static void main(String[] args) {

        Duke zenifyBot = new Duke("Zenify");
        zenifyBot.greetUser();
        zenifyBot.echo();
    }
}
