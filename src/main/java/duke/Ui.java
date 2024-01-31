package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    /**
     * Name of the bot
     */
    protected String botName;

    /**
     * Scanner to read user input
     */
    protected Scanner scanner;

    /**
     * Constructor for Ui
     * @param botName Name of the bot
     */
    public Ui(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input
     * @return Next line of user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the message if error when loading file
     */
    public void showLoadingError() {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Error loading file. Creating new file...%n");
        System.out.printf("____________________________________________________________%n");
    }

    /**
     * Shows welcome message
     */
    public void showWelcome() {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Hello! I'm %s%n", botName);
        System.out.printf(" What can I do for you?%n");
        System.out.printf("____________________________________________________________%n");

    }

    /**
     * Shows a string representation of the list of tasks
     */
    public void showTaskList(String[] tasks) {
        System.out.print("____________________________________________________________\n");
        System.out.print(" Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.length; i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks[i]);
        }

    }

    /**
     * Shows a string representation of the task marked as done
     * @param task Task being marked as done
     */
    public void showTaskMarked(Task task) {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Nice! I've marked this task as done:%n");
        System.out.printf("   %s%n", task);

    }

    /**
     * Shows a string representation of the task marked as undone
     * @param task Task being marked as undone
     */
    public void showTaskUnmarked(Task task) {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" OK, I've marked this task as not done yet:%n");
        System.out.printf("   %s%n", task);
    }

    /**
     * Shows a string representation of the task added
     * @param task Task being added
     * @param len Length of the list of tasks
     */
    public void showTaskAdded(Task task, int len) {
        System.out.println("____________________________________________________________\n");
        System.out.println( "Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + len + " tasks in the list.");
    }

    /**
     * Shows a string representation of the task deleted
     * @param task Task being deleted
     * @param len Length of the list of tasks
     */
    public void showTaskDeleted(Task task, int len) {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Noted. I've removed this task:%n");
        System.out.printf("   %s%n", task);
        System.out.printf(" Now you have %d tasks in the list.%n", len);
    }

    /**
     * Bids goodbye to the user
     */
    public void showGoodbye() {
        System.out.printf("____________________________________________________________%n");
        System.out.printf(" Bye. Hope to see you again soon!%n");
        System.out.printf("____________________________________________________________%n");

    }

    /**
     * Prints a line break
     */
    public void lineBreak() {
        System.out.println("____________________________________________________________\n");
    }

}
