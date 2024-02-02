package chatbot;

import java.util.Scanner;

/**
 * Represents the UI controller.
 */
public class Ui {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    private static final String LOGO =
            "    ____  __                 \n" +
            "   / __ \\/ /___ _____  ____ _\n" +
            "  / /_/ / / __ `/ __ \\/ __ `/\n" +
            " / ____/ / /_/ / / / / /_/ / \n" +
            "/_/   /_/\\__,_/_/ /_/\\__,_/  \n";

    private static final String NAME = "Plana";

    private final Scanner scanner;

    /**
     * Constructor for the UI class which initialises the Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the greeting banner in sysout.
     */
    public void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm " + NAME + "!");
        System.out.println("What can I do for you?");
        System.out.println("======================");
    }

    /**
     * Waits for user input.
     * @return User input.
     */
    public String getInput() {
        System.out.print(ANSI_GREEN + "> ");
        return this.scanner.nextLine();
    }

    /**
     * Utility function to display strings in stdout with color.
     * @param msg The message to display.
     */
    private void display(String msg) {
        System.out.println(ANSI_CYAN + msg + ANSI_RESET);
    }

    /**
     * Displays a formatted task list.
     *
     * @param tl The task list to display.
     */
    public void displayList(TaskList tl) {
        if (tl.toString().isEmpty()) {
            this.display("You have no tasks right now, add some!");
        } else {
            this.display("You've added the following tasks so far:\n" + tl);
        }
    }

    /**
     * Display a notification after user adds a task.
     *
     * @param tl The task list.
     * @param t The added task.
     */
    public void displayAdd(TaskList tl, Task t) {
        this.display("Got it. I've added this task:\n" +
                ">> " + t + "\n" +
                "You now have " + tl.getSize() + " tasks in the list.\n");
    }

    /**
     * Displays a notification after user marks a task.
     *
     * @param i The index of the marked task.
     */
    public void displayMark(int i) {
        this.display("Task " + i + "marked as done");
    }

    /**
     * Displays a notification after user unmarks a task.
     *
     * @param i The index of the unmarked task.
     */
    public void displayUnmark(int i) {
        this.display("Task " + i + "marked as undone");
    }

    /**
     * Displays a notification after user deletes a task.
     *
     * @param tl The task list.
     * @param t The deleted task.
     */
    public void displayDelete(TaskList tl, Task t) {
        this.display("Got it. I've removed this task:\n" +
                ">> " + t + "\n" +
                "You now have " + tl.getSize() + " tasks in the list.\n");
    }

    /**
     * Displays an error notification.
     *
     * @param e The error.
     */
    public void displayError(Exception e) {
        System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
    }

    /**
     * Displays the parting message.
     */
    public void bye() {
        this.scanner.close();
        System.out.println(ANSI_RESET + "==================");
        System.out.println("See you next time!");
    }
}
