package aurora.objects;

/** The AuroraException class represents exceptions specific to this application. */
public class AuroraException extends Exception {

    /** Message of the exception. */
    private final String exceptionMessage;

    public static final String INVALID_DELETE_FORMAT = "You've entered an invalid delete command!\n" +
            "Format: delete {Index of task in task list you wish to delete} \n" +
            "Example: delete 1 (To delete the first task in the list)";

    public static final String INVALID_FIND_FORMAT = "You've entered an invalid find command!\n" +
            "Format: find {Keyword you wish to search the task list for} \n" +
            "Example: find ballet (Returns a list of tasks which has descriptions containing the word ballet";

    public static final String INVALID_MARK_FORMAT = "You've entered an invalid mark command!\n" +
            "Format: mark {Index of task in task list you wish to mark as done} \n" +
            "Example: mark 1 (To mark the first task in the list as done)";

    public static final String INVALID_UNMARK_FORMAT = "You've entered an invalid unmark command!\n" +
            "Format: unmark {Index of task in task list you wish to unmark as done} \n" +
            "Example: unmark 1 (To unmark the first task in the list as done)";

    public static final String INVALID_TODO_FORMAT = "You've entered an invalid Todo command!\n" +
            "Format: todo {Description of the task you need to do} \n" +
            "Example: todo play the piano (Creates a new Todo task to play the piano)";
    public static final String INVALID_EVENT_FORMAT = "You've entered an invalid Event command!\n" +
            "Format: event {Description of event} /from {start date and time of event} /to \n " +
            "{end date and time of event} \n" +
            "All dates must be in dd/MM/yyyy HHmm format. \n" +
            "Example: event birthday party /from 25/02/2024 1900 /to 25/02/2024 2330";

    public static final String INVALID_DEADLINE_FORMAT = "You've entered an invalid Deadline command!\n" +
            "Format: deadline {Description of deadline} /by {Deadline date and time} \n" +
            "All dates must be in dd/MM/yyyy HHmm format. \n" +
            "Example: deadline math assignment /by 29/02/2024 2359";

    public static final String INVALID_DOAFTER_FORMAT = "You've entered an invalid DoAfter command!\n" +
            "Format: \n" +
            "Either: doafter {Description of task} /after {Date and time} \n" +
            "Or: doafter {Description of task} /after {Index of task in task list the new task is after} \n" +
            "All dates must be in dd/MM/yyyy HHmm format. \n" +
            "Example 1: doAfter dance class /after 25/02/2024 1750 \n" +
            "Example 2: doAfter return book /after 1 (Return book after the first task in the task list.)";

    public static final String INVALID_COMMAND = "I am unable to understand this command. \n" +
            "The valid commands are: list, find, todo, deadline, event, doafter, mark, unmark, delete, bye. \n" +
            "Please try one of them to use me.";

    public static final String INVALID_DATE_FORMAT = "You've entered an invalid date! \n" +
            "All dates must be in dd/MM/yyyy HHmm format. \n" +
            "Example: 25/02/2024 1933";


    /**
     * Constructs an AuroraException object.
     *
     * @param exceptionMessage Message of the exception that describes it.
     */
    public AuroraException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
