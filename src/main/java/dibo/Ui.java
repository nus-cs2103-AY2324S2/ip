package dibo;

import java.util.Scanner;

/**
 * Class to deal with the interaction with users.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs the Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Scans the whole line for the user's command and
     * returns the string representation.
     */
    public String takeCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a hello message to the users.
     */
    public void sayHi() {
        showSeparator();
        System.out.println("Hello sir! I'm Dibo.");
        System.out.println("What can I do for you today?");
        showSeparator();
    }

    /**
     * Takes in the string representation of the taskList and reports it to the user.
     *
     * @param taskList The String representation of the taskList.
     */
    public void showList(String taskList) {
        showSeparator();
        System.out.println(taskList);
        showSeparator();
    }

    /**
     * Prints the message to show that the task has been successfully unmarked.
     *
     * @param task The String representation of the task.
     */
    public void showUnmarked(String task) {
        showSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        showSeparator();
    }

    /**
     * Prints the message to show that the task has been successfully marked.
     *
     * @param task The String representation of the task.
     */
    public void showMarked(String task) {
        showSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showSeparator();
    }

    /**
     * Prints the message to show that the task has been successfully deleted.
     *
     * @param task The String representation of the task.
     */
    public void showDeleted(String task, int size) {
        showSeparator();
        System.out.println("Noted. I'm removing this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + (size > 1 ? " tasks " : " task ")
                        + "left in the list.");
        showSeparator();
    }

    /**
     * Prints the message to show that the task has been successfully added.
     *
     * @param task The String representation of the task.
     * @param size The size of the taskList after the last action performed.
     */
    public void showAdded(String task, int size) {
        showSeparator();
        System.out.println("Good news sir! I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + (size > 1 ? " tasks " : " task ")
                + "in the list.\n");
        showSeparator();
    }

    /**
     * Prints the message to show that the task has been found.
     *
     * @param tasksWithKeyword The string representation of the tasks that contains the specified keyword.
     */
    public void showFound(String tasksWithKeyword) {
        showSeparator();
        System.out.println("Good news sir! We've found the tasks in your list:");
        System.out.println(tasksWithKeyword);
        showSeparator();
    }


    /**
     * Prints the error message.
     *
     * @param errorMessage The string representation of the error.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void sayBye() {
        showSeparator();
        System.out.println("Bye sir! Always happy to assist you :D");
        System.out.println("Hope to see you again soon!");
        showSeparator();
    }
    private void showSeparator() {
        System.out.println("\n");
    }


}
