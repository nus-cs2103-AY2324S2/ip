package aurora.objects;

/**
 * The DukeException class represents exceptions specific to this application.
 */
public class AuroraException extends Exception {

    /** Message of the exception. */
    private final String exceptionMessage;

    public static final String INVALID_DELETE_FORMAT = "Invalid number of arguments!\n" +
            "Make sure to enter unmark, then the number of the task you want to delete.";

    public static final String INVALID_FIND_FORMAT = "Invalid number of arguments!\n" +
            "Make sure to enter find, then the keyword you wish to search for in the task list.";

    public static final String INVALID_MARK_FORMAT = "Invalid number of arguments!\n" +
            "Make sure to enter mark, then the number of the task you want to mark as done.";

    public static final String INVALID_UNMARK_FORMAT = "Invalid number of arguments!\n" +
            "Make sure to enter unmark, then the number of the task you want to unmark.";

    public static final String INVALID_TODO_FORMAT = "Invalid number of arguments!\n" +
            "Make sure to enter todo, then specify the task.";
    public static final String INVALID_EVENT_FORMAT = "Invalid number of arguments!\n" +
            "Make sure to enter event, " +
            "then specify the description of the task followed by the start and end " +
            "dates.\n" +
            "The start date should be preceded with /from, " +
            "while the end date should be preceded with /to.";

    public static final String INVALID_DEADLINE_FORMAT = "Invalid number of arguments!\n" +
            "Make sure to enter deadline, " +
            "then specify the description of the task followed by the deadline.\n" +
            "These two fields should be separated with /by.";

    public static final String INVALID_DOAFTER_FORMAT = "Invalid command!\n" +
            "Make sure to enter doafter, " +
            "then specify the description of the task followed by" +
            "either the date and time in dd/MM/yyyy HHmm format " +
            "or \n" +
            "integer representing the task (in the list) this doAfter needs to be " +
            "performed after.\n" +
            "These two fields should be separated by /after.";

    public static final String INVALID_COMMAND = "I am unable to understand this command, please kindly try again.";

    public static final String INVALID_DATE_FORMAT = "Invalid date format. " +
            "Please use dd/MM/yyyy HHmm format for deadlines.";


    /**
     * Constructor of the DukeException class.
     *
     * @param exceptionMessage Message of the exception that describes it.
     */
    public AuroraException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Getter for the exception message.
     *
     * @return Message that describes the exception.
     */
    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
