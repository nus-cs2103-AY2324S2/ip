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

    public void echo() {

        Scanner myScannerObj = new Scanner(System.in);
        System.out.print("Message Zenify: ");
        while (myScannerObj.hasNext()) {
            System.out.print(this.horizontalLine);
            String task = myScannerObj.nextLine();
            if (task.equalsIgnoreCase("bye")) {
                this.exit();
                break;
            } else if (task.equalsIgnoreCase("list")) {
                this.toDoList();
            } else if (task.startsWith("mark")){
                this.markToDo(task, true);
            } else if (task.startsWith("unmark")) {
                this.markToDo(task, false);
            } else {
                this.addToDo(task);
            }
            System.out.print(this.horizontalLine);
            System.out.print("\nMessage Zenify: ");
        }

        myScannerObj.close();

    }

    private void addToDo(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println(" added: " + description);
    }

    private void toDoList() {
        if (tasks.isEmpty()) {
            System.out.println(" Please add task!");
        } else {
            System.out.println("Here are the tasks in your list:");
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
                Task task = this.tasks.get(taskIndex);
                task.setDone(isDone);
                System.out.println(isDone ? " Nice! I've marked this task as done:" :
                        " OK, I've marked this task as not done yet:");
                System.out.println("   " + task);
            } else {
                System.out.println("Task not found: Index is out of bounds.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(" Invalid command format.");
        }
    }

    public static void main(String[] args) {

        Duke zenifyBot = new Duke("Zenify");
        zenifyBot.greetUser();
        zenifyBot.echo();
    }
}
