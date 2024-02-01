import java.util.Scanner;
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Cookie");
        System.out.println("What can I do for you?");
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAddTaskMessage(Task t, int counter) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + (counter + 1) + " tasks in the list.");
    }

    public void showRemoveTaskMessage(Task t, int counter) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + (counter - 1) + " tasks in the list.");
    }

    public void showMarkTaskDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskList(Task[] tasks, int counter) {
        for (int i = 1; i <= counter; i++) {
            Task task = tasks[i - 1];
            String taskDesc = task.toString();
            System.out.println(i + "." + taskDesc);
        }
    }
}
