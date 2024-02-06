package duke;
import java.util.Scanner;
import task.Task;
import java.util.ArrayList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private final String LINE = "\t____________________________________________________________";
    private Scanner sc = new Scanner(System.in);

    /**
     * Reads the next line of input from the user.
     * @return The next line of input from the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows a line to the user.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Shows the greetings to the user.
     */
    public void sayGreetings() {
        this.showLine();
        System.out.println("\tHello! I'm SKY");
        System.out.println("\tWhat can I do for you?");
        this.showLine();
    }

    /**
     * Shows the goodbye message to the user.
     */
    public void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     * Shows an error message to the user.
     * @param e Exception to show the error message for.
     */
    public void showErrorMessage(Exception e) {
        System.out.println("\t" + e);
        this.showLine();
    }

    /**
     * Shows the added task to the user.
     * @param task Task to show the user.
     * @param size Size of the task list.
     */
    public void showAddTask(Task task, int size) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        this.showLine();
    }

    /**
     * Shows the list of tasks to the user.
     * @param list List of tasks to show the user.
     */
    public void showList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
        this.showLine();
    }

    /**
     * Shows the marked task to the user.
     * @param task Task to show the user.
     */
    public void showMarkTask(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task);
        this.showLine();
    }

    /**
     * Shows the unmarked task to the user.
     * @param task Task to show the user.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task);
        this.showLine();
    }

    /**
     * Shows the deleted task to the user.
     * @param task Task to show the user.
     * @param size Size of the task list.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        this.showLine();
    }

    /**
     * Shows the corrupted data message to the user.
     */
    public void showCorruptedData() {
        System.out.println("\tData file is corrupted. Starting with an empty list.");
        this.showLine();
    }
}
