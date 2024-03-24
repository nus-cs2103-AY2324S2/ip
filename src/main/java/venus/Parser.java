package venus;

/**
 * This is a Parser class that make strings understandable for the task formatters.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Parser {
    /**
     * Returns TaskList.TYPES in TaskList class that returns given an input from console.
     *
     * @param input The input from console itself.
     * @return TASKList.TYPES that determine what action is taken by method setTask in
     *          the TaskList class.
     * @throws IllegalArgumentException If we have invalid input task type, this is then
     *                                   the exception is handled by the caller.
     */
    public static TaskList.Types findType(String input) throws IllegalArgumentException {
        String[] listType = input.toUpperCase().split(" ");
        TaskList.Types type = TaskList.Types.valueOf(listType[0]);
        boolean isAllType = listType.length == 1 && listType[0].equals("LIST");
        if (isAllType) {
            type = TaskList.Types.ALL;
        }
        boolean isFindDuplicate = listType.length == 1 && listType[0].equals("DUPLICATE");
        if (isFindDuplicate) {
            type = TaskList.Types.DUPLICATE;
        }
        return type;
    }

    /**
     * Returns an int that correspond to location of the item in TaskList instance.
     *
     * @param input Input to be parsed.
     * @return Integer location of the item.
     */
    public static int findMarkIndex(String input) {
        return Integer.valueOf(input.substring(5)) - 1;
    }

    /**
     * Returns an int that correspond to location of the item in TaskList instance.
     *
     * @param input Input to be parsed.
     * @return Integer location of the item.
     */
    public static int findUnmarkIndex(String input) {
        return Integer.valueOf(input.substring(7)) - 1;
    }

    /**
     * Returns a string that finds content of Todo class.
     *
     * @param input Input to be parsed.
     * @return String Todo content.
     */
    public static String findTodoContent(String input) {
        return input.substring(5);
    }

    /**
     * Returns a string that finds content and date of Deadline class.
     *
     * @param input Input to be parsed.
     * @return String array of Deadline content and time (unformatted).
     */
    public static String[] findDeadlineContent(String input) throws DukeException {
        String dString = input.substring(9);
        String[] parts = dString.split("/by");
        if (parts.length != 2) {
            throw new DukeException("Incorrect, choose a specific deadline only please");
        }
        parts[0] = parts[0].trim();
        parts[1] = parts[1].trim();
        return parts;
    }

    /**
     * Returns a string that finds content and dates of Event class.
     *
     * @param input Input to be parsed.
     * @return String array of Event content, start and end time(unformatted).
     */
    public static String[] findEventParts(String input) throws DukeException {
        String dString = input.substring(6);
        String[] parts = dString.split("/");
        if (parts.length != 3) {
            throw new DukeException("Incorrect arguments for events");
        }
        for (int j = 0; j < 3; j++) {
            parts[j] = parts[j].trim();
        }
        return parts;
    }

    /**
     * Returns an int that finds index of content to be deleted.
     *
     * @param input Input to be parsed.
     * @return To be deleted content location.
     */
    public static int findDeleteIndex(String input) {
        return Integer.valueOf(input.substring(7)) - 1;
    }

    /**
     * Returns a String that finds keyword of content to be listed.
     *
     * @param input Input to be parsed.
     * @return Keyword to be used for search.
     */
    public static String findFindKeyword(String input) {
        return input.substring(4).trim();
    }
}
