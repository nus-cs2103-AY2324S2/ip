package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import bob.task.Task;

/**
 * Represents the user interface (UI) of the program. A <code>Ui</code> object corresponds to
 * a user interface which the program can apply.
 */
public class Ui {

    private static final int INDENT_SPACE_COUNT = 4;

    private static final String EXIT = "ok";
    private static final String[] GREET = new String[]{ "yo im bob", "what do you want" };
    private static final String LINE =
            ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.";
    private static final String LOADING_ERROR = "wait what happened i cant load any data";

    private static final String HEADER_ADD = "added:";
    private static final String HEADER_DELETE = "removed:";
    private static final String HEADER_FIND = "matching tasks:";
    private static final String HEADER_LIST = "list of tasks:";
    private static final String HEADER_MARK = "good job!";
    private static final String HEADER_UNMARK = "ok you just undid this task";

    private static final String FOOTER_DELETE = "%d task(s) left";
    private static final String FOOTER_NUMBER_OF_TASKS = "now you have %d task(s)";

    private static final String PATTERN_DATE_TIME = "MMM dd yyyy HHmm";

    private static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);

    private final Scanner scanner;

    /**
     * Initialises the scanner to be used to read inputs from the user.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user.
     *
     * @return The read command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Utility method to format the given <code>LocalDateTime</code>
     * using the predefined <code>DateTimeFormatter</code>.
     *
     * @param dateTime The given <code>LocalDateTime</code>.
     * @return The formatted string to be displayed to the user.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_DATE_TIME);
    }

    /**
     * Displays the line.
     */
    public void showLine() {
        System.out.println(" ".repeat(INDENT_SPACE_COUNT) + LINE);
    }

    /**
     * Displays the specified lines of output to the user after applying indentations.
     *
     * @param lines The lines of output to be indented and displayed to the user.
     */
    public void show(String[] lines) {
        for (String line : lines) {
            System.out.println(" ".repeat(INDENT_SPACE_COUNT + 1) + line);
        }
    }

    /**
     * Indents and displays a specified line of output to the user.
     *
     * @param line The line to be indented and displayed to the user.
     */
    public void show(String line) {
        show(new String[]{ line });
    }

    /**
     * Displays the loading error message.
     *
     * @param message The error message to be displayed.
     */
    public void showLoadingError(String message) {
        show(new String[] { LOADING_ERROR, message });
    }

    /**
     * Greets the user.
     */
    public void showWelcome() {
        showLine();
        show(GREET);
        showLine();
    }

    // The methods below all display the result of a command.
    // As we have more and more commands, it might be better to have CommandResult classes that encapsulates
    // whatever is to be displayed after each command.

    /**
     * Displays the result of adding a task.
     *
     * @param task The added task.
     * @param numberOfTasks The number of tasks after adding the task.
     */
    public void showAdd(Task task, int numberOfTasks) {
        show(new String[] {
            HEADER_ADD,
            " ".repeat(2) + task,
            String.format(FOOTER_NUMBER_OF_TASKS, numberOfTasks)
        });
    }

    /**
     * Displays the result of marking or unmarking a task.
     *
     * @param task The marked or unmarked task.
     * @param isDone Whether the task is marked or unmarked.
     */
    public void showMark(Task task, boolean isDone) {
        show(new String[] { isDone ? HEADER_MARK : HEADER_UNMARK, " ".repeat(2) + task });
    }

    /**
     * Displays the result of deleting a task.
     *
     * @param task The deleted task.
     * @param numberOfTasks The number of tasks after deleting the task.
     */
    public void showDelete(Task task, int numberOfTasks) {
        show(new String[] {HEADER_DELETE, " ".repeat(2) + task, String.format(FOOTER_DELETE, numberOfTasks) });
    }

    /**
     * Displays a specified list of tasks.
     *
     * @param tasks The specified list of tasks to be displayed.
     * @param header The header of the display.
     */
    public void showList(ArrayList<Task> tasks, String header) {
        // Prepare an array to store the lines to display, setting the first element as the header line
        String[] lines = new String[tasks.size() + 1];
        lines[0] = header;

        // Go through the given list and add them into lines
        for (int i = 0; i < tasks.size(); i++) {
            lines[i + 1] = (i + 1) + ". " + tasks.get(i);
        }

        // Display the lines
        show(lines);
    }

    /**
     * Displays a specified list of tasks using the default list header.
     *
     * @param tasks The specified list of tasks to be displayed.
     */
    public void showList(ArrayList<Task> tasks) {
        showList(tasks, HEADER_LIST);
    }

    /**
     * Displays a specified list of tasks using the find header.
     *
     * @param tasks The specified list of tasks to be displayed.
     */
    public void showFind(ArrayList<Task> tasks) {
        showList(tasks, HEADER_FIND);
    }

    /**
     * Displays the exit message.
     */
    public void showExit() {
        show(EXIT);
    }
}
