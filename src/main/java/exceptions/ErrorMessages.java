package exceptions;

/**
 * This class contains all the error messages used in the Tam program.
 */
public class ErrorMessages {
    public static final String COMMAND_NOT_FOUND =
            "Invalid command entered. "
                    + "Type 'help' for list of commands and their usages.\n";

    public static final String INCORRECT_PARAMETERS =
            "Incorrect number of parameters detected. "
                    + "Type 'help' for list of commands and their usages.\n";

    public static final String TASK_NUMBER_PARSE_ERROR =
            "Invalid task number entered!\n";

    public static final String TASK_NUMBER_DOES_NOT_EXIST =
            "Task number does not exist! "
                    + "Type 'list' to check list\n";

    public static final String MISSING_TASK_DESCRIPTION =
            "Missing task description!\n";

    public static final String DUE_DATE_NOT_NEEDED =
            "This task type cannot have a due date!\n";

    public static final String FROM_TO_DATE_NOT_NEEDED =
            "This task type cannot have a from and to date!\n";

    public static final String MISSING_DUE_DATE =
            "Missing due date!\n";

    public static final String INCORRECT_DATE_FORMAT =
            "Incorrect formatting of due date! Ensure it is in yyyy-mm-dd format\n";

    public static final String MISSING_FROM_TO_DATE =
            "Missing from and/or to date!\n";

    public static final String MISSING_FROM_DATE =
            "Missing from date!\n";

    public static final String MISSING_TO_DATE =
            "Missing to date!\n";
}
