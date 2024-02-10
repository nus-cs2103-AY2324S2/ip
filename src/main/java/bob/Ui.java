package bob;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ui {
    private static final int INDENT_SPACE_COUNT = 4;

    public static final String HORIZONTAL_LINE
            = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.";

    public static final String[] GREET = new String[]{ "yo im bob", "what do you want" };

    public static final String ADD_HEADER = "added:";
    public static final String NUMBER_OF_TASKS = "now you have %d task(s)";

    public static final String LIST_HEADER = "list of tasks:";

    public static final String MARK_HEADER = "good job!";
    public static final String UNMARK_HEADER = "ok you just undid this task";

    public static final String DELETE_HEADER = "removed:";
    public static final String DELETE_FOOTER = "%d task(s) left";

    public static final String EMPTY_DESCRIPTION = "missing description for %s";
    public static final String INVALID_COMMAND = "what";
    public static final String PARAMETER_NOT_FOUND = "not enough information: %s";
    public static final String INVALID_TASK_INDEX = "invalid task index. "
            + "task index is non-zero integer at most the number of tasks you have. "
            + "you gave me \"%s\".";

    public static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    public static final String INVALID_DATE_FORMAT = "i only understand " + Parser.INPUT_DATE_PATTERN + " bro";
    public static final String INVALID_DATETIME_FORMAT
            = "i only understand " + Parser.INPUT_DATETIME_PATTERN + " bro";

    public static final String LOADING_ERROR = "wait what happened i cant load any data";

    public static final String SAVING_ERROR = "wait what happened i cant seem to save";

    public static final String EXIT = "ok";

    private static String indentAndBreak(String line, int indentSpaceCount) {
        return " ".repeat(indentSpaceCount) + line + '\n';
    }

    public static void print(String[] lines) {
        String formattedHorizontalLine = indentAndBreak(HORIZONTAL_LINE, INDENT_SPACE_COUNT);

        StringBuilder output = new StringBuilder(formattedHorizontalLine);

        for (String line : lines) {
            output.append(indentAndBreak(line, INDENT_SPACE_COUNT + 1));
        }

        output.append(formattedHorizontalLine);

        System.out.println(output);
    }

    public static void print(String line) {
        print(new String[]{ line });
    }

    public static void add(Task task, int numberOfTasks) {
        print(new String[] {
                ADD_HEADER,
                " ".repeat(2) + task,
                String.format(NUMBER_OF_TASKS, numberOfTasks)
        });
    }

    public static void list(ArrayList<Task> tasks) {
        String[] lines = new String[tasks.size() + 1];
        lines[0] = LIST_HEADER;

        for (int i = 0; i < tasks.size(); i++) {
            lines[i + 1] = (i + 1) + ". " + tasks.get(i);
        }

        print(lines);
    }

    public static void mark(Task task, boolean done) {
        print(new String[] { done ? MARK_HEADER : UNMARK_HEADER, " ".repeat(2) + task });
    }

    public static void delete(Task task, int numberOfTasks) {
        print(new String[] { DELETE_HEADER, " ".repeat(2) + task, String.format(DELETE_FOOTER, numberOfTasks) });
    }
}
