import java.util.Scanner;

public class Ui {
    private final String DIVIDER = "    ____________________________________________________________";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printWelcome(String name) {
        this.printDivider();
        System.out.println("     Hello! I'm " + name + "\uD83E\uDD5A.");
        System.out.println("     What can I do for you?");
        this.printDivider();
    }

    public void printGoodbye() {
        this.printDivider();
        System.out.println("     Bye\uD83D\uDC4B. Hope to see you again soon!");
        this.printDivider();
    }

    public void printTaskAdded(Task task, int taskCount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    public void printTaskRemoved(Task task, int taskCount) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    public void printTaskMarkedDone(Task task) {
        System.out.println("     Nice! I've marked this task as done:\n       " + task.toString());
    }

    public void printTaskUnmarkedDone(Task task) {
        System.out.println("     OK, I've marked this task as not done yet:\n       " + task.toString());
    }

    public void printTaskList(TaskList tasks) {
        System.out.println("     Here are the tasks in your list:");
        System.out.println(tasks.toString());
    }

    public void printTaskListEmpty() {
        System.out.println("     There are no tasks in your list.");
    }

    public void printException(String message) {
        System.out.println("     " + message);
    }
}
