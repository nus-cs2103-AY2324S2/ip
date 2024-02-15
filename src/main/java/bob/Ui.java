package bob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import bob.task.Task;

public class Ui {
    private final Scanner scanner;

    private static final int INDENT_SPACE_COUNT = 4;

    private static final String EXIT = "ok";
    private static final String[] GREET = new String[]{ "yo im bob", "what do you want" };
    private static final String LINE
            = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.";
    private static final String LOADING_ERROR = "wait what happened i cant load any data";

    private static final String HEADER_ADD = "added:";
    private static final String HEADER_DELETE = "removed:";
    private static final String HEADER_LIST = "list of tasks:";
    private static final String HEADER_MARK = "good job!";
    private static final String HEADER_UNMARK = "ok you just undid this task";

    private static final String FOOTER_DELETE = "%d task(s) left";
    private static final String FOOTER_NUMBER_OF_TASKS = "now you have %d task(s)";

    private static final String PATTERN_DATE_TIME = "MMM dd yyyy HHmm";

    private static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_DATE_TIME);
    }

    public void showLine() {
        System.out.println(" ".repeat(INDENT_SPACE_COUNT) + LINE);
    }

    public void show(String[] lines) {
        for (String line : lines) {
            System.out.println(" ".repeat(INDENT_SPACE_COUNT + 1) + line);
        }
    }

    public void show(String line) {
        show(new String[]{ line });
    }

    public void showLoadingError(String message) {
        show(new String[] { LOADING_ERROR, message });
    }

    public void showWelcome() {
        showLine();
        show(GREET);
        showLine();
    }

    // The methods below all display the result of a command.
    // As we have more and more commands, it might be better to have CommandResult classes that encapsulates
    // whatever is to be displayed after each command.
    public void showAdd(Task task, int numberOfTasks) {
        show(new String[] {
                HEADER_ADD,
                " ".repeat(2) + task,
                String.format(FOOTER_NUMBER_OF_TASKS, numberOfTasks)
        });
    }

    public void showMark(Task task, boolean done) {
        show(new String[] { done ? HEADER_MARK : HEADER_UNMARK, " ".repeat(2) + task });
    }

    public void showDelete(Task task, int numberOfTasks) {
        show(new String[] {HEADER_DELETE, " ".repeat(2) + task, String.format(FOOTER_DELETE, numberOfTasks) });
    }
    public void showList(ArrayList<Task> tasks) {
        // Prepare an array to store the lines to display, setting the first element as the header line
        String[] lines = new String[tasks.size() + 1];
        lines[0] = HEADER_LIST;

        // Go through the given list and add them into lines
        for (int i = 0; i < tasks.size(); i++) {
            lines[i + 1] = (i + 1) + ". " + tasks.get(i);
        }

        // Display the lines
        show(lines);
    }

    public void showExit() {
        show(EXIT);
    }
}
