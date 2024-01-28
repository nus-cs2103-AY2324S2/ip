public class Replies {
    public static final String HORIZONTAL_LINE
            = "    .-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";

    public static final String[] GREET = new String[]{ "yo im bob", "what do you want" };

    public static final String EXCEEDED_MAX_NUMBER_OF_TASKS = "not enough brain power";
    public static final String ADD = "added: %s";

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

    public static void list(String[] tasks, int numberOfTasks) {
        String[] numberedTasks = new String[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            numberedTasks[i] = (i + 1) + ". " + tasks[i];
        }

        print(numberedTasks);
    }
}
