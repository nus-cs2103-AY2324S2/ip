package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui instance, initializing the scanner to read from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Lucifer\nWhat can I do for you?");
        System.out.println("______________________________________________________");
    }

    /**
     * Prints a divider line to the console to separate different parts of the output visually.
     */
    public void divider() {
        System.out.println("______________________________________________________");
    }

    /**
     * Reads a command from the user input.
     *
     * @return A string containing the user's command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a task to the user.
     *
     * @param task The string representation of the task to be displayed.
     */
    public void showTask(String task) {
        System.out.println(" " + task);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________________________________________");
    }

}
