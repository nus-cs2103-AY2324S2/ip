package earl.util;

import earl.exceptions.EarlException;
import earl.tasks.Task;
import earl.tasks.TaskFactory;

/**
 * Class responsible for parsing user and stored entries.
 */
public class Parser {

    /**
     * Returns a new {@code Task} object based on stored entry.
     *
     * @param entry           a line of text stored in the data file
     * @return                a {@code Task} object of the relevant type
     * @throws EarlException  if stored entry is of unexpected format
     */
    public static Task parseStorageEntry(String entry) throws EarlException {
        String[] task = entry.split(",");
        return TaskFactory.of(task);
    }

    /**
     * Returns the command and the command arguments given the
     * user's input.
     * <p>
     * Splits the user's input at the first space in order to obtain
     * an array of two strings, the first of which is the command.
     *
     * @param input  a line input by the user
     * @return       an array of two {@code String} objects
     */
    public static String[] parseUserInput(String input) {
        return input.split("\\s", 2);
    }

    /**
     * Overloaded method to split the user's input at a given regex.
     *
     * @param input  a line input by the user
     * @param regex  the regex pattern to split at
     * @return       an array of {@code String} split as specified
     */
    public static String[] parseUserInput(String input, String regex) {
        return input.split(regex);
    }

    /** Returns the integer index equivalent of the user's selection. */
    public static int parseIndex(String index) {
        return Integer.parseInt(index) - 1;
    }
}
