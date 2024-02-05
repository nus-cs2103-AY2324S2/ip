package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import task.TaskList;

/**
 * The Ui class is responsible for managing user interface interactions, including
 * displaying messages, getting user input, and presenting the task list to the user.
 */
public class Ui {

    private static final String LINE = "――――――――――――――――――――――――――――――――――――――――――――――――――";
    private static final String MESSAGE_WELCOME = "Hello! I'm Uncle Bob \n\t What can uncle do for you?";
    private static final String MESSAGE_BYE = "Bye! Hope to see you again soon!";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Default constructor for Ui, using System.in for input and System.out for output.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor for Ui that allows customization of input and output streams.
     *
     * @param in  The input stream, typically representing user input.
     * @param out The print stream, typically representing output.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays a list of tasks in the task list.
     * @param tasks The TaskList representing the collection of tasks.
     */
    public void showList(String message, TaskList tasks) {
        out.println("\t" + LINE);
        out.println("\t" + message);
        for (int i = 0; i < tasks.numTasks(); i++) {
            out.println("\t " + (i + 1) + ". " + tasks.get(i));
        }
        out.println("\t" + LINE);
    }

    /**
     * Displays error messages.
     * @param message Error messages encountered.
     */
    public void showErrorMessage(String message) {
        showToUser(message);
    }

    /**
     * Seek user input.
     * @return Reads in the next line of user input as a String.
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        return in.nextLine();
    }

    /**
     * Displays default greeting message.
     */
    public void showGreetingMessage() {
        showToUser(MESSAGE_WELCOME);
    }

    /**
     * Displays default exit message.
     */
    public void showExitMessage() {
        showToUser(MESSAGE_BYE);
    }

    /**
     * Display message to user with lines and tabs to show that it is a program output.
     * @param message Message to be displayed to user.
     */
    public void showToUser(String... message) {
        out.println("\t" + LINE);
        for (String m : message) {
            out.print("\t " + m + "\n");
        }
        out.println("\t" + LINE);
    }
}
