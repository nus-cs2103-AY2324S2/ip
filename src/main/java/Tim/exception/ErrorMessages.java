package Tim.exception;

/**
 * Container for error messages.
 */
public class ErrorMessages {

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n"
            + "Use 'help' to learn more about usage.";
    public static final String MESSAGE_UNKNOWN_COMMAND = "What is this task??";

    public static final String MESSAGE_DUPLICATE_TASK = "The task had already been added to the list previously!";

    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid Date format!\n"
            + "Date should be of 'dd/mm/yyyy' format.";

    public static final String MESSAGE_INVALID_INDEX = "Invalid Task Number!\n"
            + "Use 'List' to see tasks in list.";

    public static final String MESSAGE_NO_MATCH_FOUND = "There is no matching Tasks!";
}
