package podz.ui;

import java.util.Scanner;

import podz.task.Task;

/**
 * Represents the user interface for interacting with the task manager.
 */
public class Ui {
    private static final String DIV = "____________________________________________________________";
    private Scanner sc;

    /**
     * Constructs a Ui object with a Scanner for user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Retrieves user input.
     *
     * @return the user input
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Prints a greeting message.
     */
    public void printGreeting() {
        printDiv();
        System.out.println("\tHello! I'm Podz.");
        System.out.println("\tWhat can I do for you?");
        printDiv();
    }

    /**
     * Prints a farewell message.
     */
    public void printBye() {
        printToUser("\tBye. Hope to see you again soon!");
    }

    /**
     * Prints messages to the user.
     *
     * @param message the messages to be printed
     */
    public void printToUser(String... message) {
        printDiv();
        for (String m : message) {
            System.out.println(m);
        }
        printDiv();
    }

    /**
     * Prints an error message.
     *
     * @param e the exception containing the error message
     */
    public void printError(Exception e) {
        printToUser("\t" + e.getMessage());
    }

    /**
     * Prints a message indicating a task has been marked as done.
     *
     * @param task the task that has been marked as done
     */
    public void printMarked(Task task) {
        printToUser("\tNice! I've marked this task as done: ",
                    "\t" + task);
    }

    /**
     * Prints a message indicating a task has been marked as not done yet.
     *
     * @param task the task that has been marked as not done yet
     */
    public void printUnmarked(Task task) {
        printToUser("\tOK, I've marked this task as not done yet: ",
                    "\t" + task);
    }

    private void printDiv() {
        System.out.println("\t" + DIV);
    }
}
