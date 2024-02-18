package squid.constants;

/**
 * The class of constants for Strings regarding exception messages.
 */
public class Exceptions {
    public static final String INCORRECT_INPUT = "You haven't taught me that trick yet..";
    public static final String NOT_ENOUGH_INPUTS = "hmm, %s what?\nUsage: %s";
    public static final String NOT_ENOUGH_DATES =
            "You need a name for the task and a total of %d date(s) for a %s task!\nUsage: %s";
    public static final String DUPLICATE_TASK_NAME = "There already exists a task with the name %s!";
    public static final String INCORRECT_INDEX = "Please enter a valid integer!\nUsage: %s";
    public static final String PARSE_FAIL = "Not parsing a Task String: \n%s";
    public static final String SQUID_DATE =
            "I can't read this! \n%s\nInput the %s in a cat-readable format, something like:\n%s";
}
