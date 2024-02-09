package squid.constants;

/**
 * The class for formatting Help messages.
 */
public class Help {

    public static final String USAGE = " Usage: ";
    public static final String DESC_TODO = "todo: Adds a task without a deadline or timeframe.\n";
    public static final String DESC_ECHO = "echo: Gets Squid to repeat after you.\n";
    public static final String DESC_EVENT = "event: Adds a task with a from and to date.\n";
    public static final String DESC_DEADLINE = "deadline: Adds a task with a deadline.\n";
    public static final String DESC_DELETE = "delete: Deletes a task at the specified index.\n";
    public static final String DESC_FIND = "find: Finds tasks with matching keywords.\n";
    public static final String DESC_HELP = "help: Gets list of commands\n";

    /**
     * Creates and returns the description of mark or unmark command.
     * @param isComplete whether it is a mark or unmark command.
     * @return the string representation of the mark or unmark message.
     */
    private static final String descMark(boolean isComplete) {
        return isComplete
                ? "mark: Marks a task as complete."
                : "unmark: Marks a task as incomplete.";
    }

    /**
     * Creates and returns the string representation of the help message.
     * @return the string representation of the help message.
     */
    public static final String getHelpMessage() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(DESC_DEADLINE);
        stringBuilder.append(CorrectUsage.DEADLINE + "\n\n");
        stringBuilder.append(DESC_DELETE);
        stringBuilder.append(CorrectUsage.DELETE + "\n\n");
        stringBuilder.append(DESC_ECHO);
        stringBuilder.append(CorrectUsage.ECHO + "\n\n");
        stringBuilder.append(DESC_EVENT);
        stringBuilder.append(CorrectUsage.EVENT + "\n\n");
        stringBuilder.append(DESC_FIND);
        stringBuilder.append(CorrectUsage.FIND + "\n\n");
        stringBuilder.append(DESC_HELP);
        stringBuilder.append(CorrectUsage.HELP + "\n\n");
        stringBuilder.append(descMark(true));
        stringBuilder.append(CorrectUsage.mark(true) + "\n\n");
        stringBuilder.append(DESC_TODO);
        stringBuilder.append(CorrectUsage.TODO + "\n\n");
        stringBuilder.append(descMark(false));
        stringBuilder.append(CorrectUsage.mark(false) + "\n\n");

        return stringBuilder.toString();
    }
}
