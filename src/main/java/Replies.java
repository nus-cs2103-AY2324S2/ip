public class Replies {
    public static final String HORIZONTAL_LINE
            = "    .-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";

    public static final String[] GREET = new String[]{ "yo im bob", "what do you want" };

    public static final String EXCEEDED_MAX_NUMBER_OF_TASKS = "not enough brain power";
    public static final String ADD = "added: %s";

    public static final String MARK_HEADER = "Nice! I've marked this task as done:";
    public static final String UNMARK_HEADER = "OK, I've marked this task as not done yet:";

    public static final String EXIT = "ok";

    public static void print(String[] lines) {
        StringBuilder formatted = new StringBuilder(HORIZONTAL_LINE);

        for (String line : lines) {
            formatted.append("     ");
            formatted.append(line);
            formatted.append('\n');
        }

        formatted.append(HORIZONTAL_LINE);

        System.out.println(formatted);
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
        print(new String[] { MARK_HEADER, "  " + task });
    }

    public static void unmark(Task task) {
        print(new String[] { UNMARK_HEADER, "  " + task });
    }
}
