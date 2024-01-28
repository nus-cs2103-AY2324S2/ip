public class Replies {
    private static final int INDENT_SPACE_COUNT = 4;

    public static final String HORIZONTAL_LINE
            = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.";

    public static final String[] GREET = new String[]{ "yo im bob", "what do you want" };

    public static final String EXCEEDED_MAX_NUMBER_OF_TASKS = "not enough brain power";
    public static final String ADD = "added: %s";

    public static final String LIST_HEADER = "list of tasks:";

    public static final String MARK_HEADER = "good job!";
    public static final String UNMARK_HEADER = "ok you just undid this task";

    public static final String INVALID_COMMAND = "what";

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

    public static void list(Task[] tasks, int numberOfTasks) {
        String[] list = new String[numberOfTasks + 1];
        list[0] = LIST_HEADER;

        for (int i = 0; i < numberOfTasks; i++) {
            list[i + 1] = (i + 1) + ". " + tasks[i];
        }

        print(list);
    }

    public static void mark(Task task, boolean done) {
        print(new String[] { done ? MARK_HEADER : UNMARK_HEADER, " ".repeat(2) + task });
    }
}
