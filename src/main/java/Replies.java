public class Replies {
    private static final int INDENT_SPACE_COUNT = 4;

    public static final String HORIZONTAL_LINE
            = ".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.";

    public static final String[] GREET = new String[]{ "yo im bob", "what do you want" };

    public static final String EXCEEDED_MAX_NUMBER_OF_TASKS = "not enough brain power";
    public static final String ADD = "added: %s";

    public static final String MARK_HEADER = "Nice! I've marked this task as done:";
    public static final String UNMARK_HEADER = "OK, I've marked this task as not done yet:";

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
        String[] numberedTasks = new String[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            numberedTasks[i] = (i + 1) + ". " + tasks[i];
        }

        print(numberedTasks);
    }

    public static void mark(Task task) {
        print(new String[] { MARK_HEADER, " ".repeat(2) + task });
    }

    public static void unmark(Task task) {
        print(new String[] { UNMARK_HEADER, " ".repeat(2) + task });
    }
}
