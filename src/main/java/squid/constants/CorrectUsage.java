package squid.constants;

/**
 * The class of constants for Strings regarding correct usage of commands.
 */
public class CorrectUsage {
    public static final String TODO = "todo [task]";
    public static final String ECHO = "echo [message]";
    public static final String EVENT = "event [task] /from [date] /to [date]";
    public static final String DEADLINE = "deadline [task] /by [date]";
    public static final String DELETE = "delete [index]";
    public static final String DATE = "hh:mm:ss, DD-MM-YYYY";
    public static final String FIND = "find [keyword]";
    public static final String HELP = "help";
    public static final String mark(boolean isMark) {
        return isMark ? "mark [task]" : "unmark [task]";
    }
}
