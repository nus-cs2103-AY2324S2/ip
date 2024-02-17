package cat.ui;

import java.util.Scanner;

import cat.task.Task;

/**
 * The user interface management class. This class is the centralized interface for input and output to the program.
 */
public class Ui {
    private final Scanner scanner;
    private StringBuilder outputBuffer;

    /**
     * The constructor of the user interface.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        outputBuffer = new StringBuilder();
    }

    /**
     * Flushes the output of the program at that state as a String.
     */
    public String flush() {
        var out = outputBuffer.toString();
        outputBuffer = new StringBuilder();
        return out;
    }

    /**
     * Shows the ending message.
     */
    public void showBye() {
        System.out.println("The cat recedes into the wall with a bored look on its face");
    }

    /**
     * Prints a horizontal line on the screen. Used to visually separate sections apart.
     */
    public void showLine() {
        outputBuffer.append("─".repeat(72)).append('\n');
    }

    /**
     * Shows the error encountered by the program to the user.
     */
    public void showError(Exception e) {
        assert e != null : "The exception must not be null";

        outputBuffer.append("The cat tilts its head and hands you an error report:\n").append(e.getMessage());
    }

    /**
     * Shows a note to the user.
     */
    public void showNote(String str) {
        assert str != null : "Note string must not be null";

        outputBuffer.append("The cat hands a note to you, it reads:\n").append(str);
    }

    /**
     * Tells the user that the command used is not recognized.
     */
    public void showCommandNotFound(String command) {
        assert command != null : "Command name must not be null";
        outputBuffer.append("The cat tilts its head. It doesn't know what command \"").append(command).append("\" is.");
    }

    /**
     * Shows an added task.
     *
     * @param task task that was added
     */
    public void showAddedTask(Task task) {
        assert task != null : "Task must not be null";
        outputBuffer.append("The cat scratches a mark on the wall and then hands you a receipt:\nAdded task ")
                .append(task.describe());
    }

    /**
     * Reads a command from the user.
     *
     * @return a string with a line of the user's input, or "bye" if input is empty
     */
    public String readCommand() {
        if (!scanner.hasNextLine()) {
            return "bye";
        }
        return scanner.nextLine();
    }

    /**
     * Show the help info to the user
     */
    public void showHelp() {
        showTerminology();
        showCommands();
    }

    private void showTerminology() {
        outputBuffer.append("Words wrapped with <> represent required parameters.\n");
    }

    /**
     * Show the command the program understands
     */
    private void showCommands() {
        outputBuffer.append("The allowed commands are as follows: \n")
                .append("todo <description>\n")
                .append("deadline <description> /by <time>\n")
                .append("event <description> /from <time> /by <time>\n")
                .append("mark <index>\n")
                .append("unmark <index>\n")
                .append("delete <index>\n")
                .append("list\n")
                .append("bye");
    }
}
