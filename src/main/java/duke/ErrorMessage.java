package duke;

public class ErrorMessage {

    public static final String HELP_STRING = "\tOOPS!!! That is not a valid command!\n"
            + "\tTry the following: \n"
            + "\tlist\n"
            + "\tmark x\n"
            + "\tunmark x\n"
            + "\tdelete x\n"
            + "\tfind xxx\n"
            + "\ttodo xxx\n"
            + "\tdeadline xxx /by yyyy-MM-dd\n"
            + "\tevent xxx /from yyyy-MM-dd /to yyyy-MM-dd";
    public static final String FILE_NOT_FOUND = "\tFile not found at path: ";
    public static final String IO_EXCEPTION = "\tAn error occured while trying to access the file!";
    public static final String INVALID_COMMAND = "Invalid command!";
    public static final String EMPTY_TODO_DESCRIPTION = "Task description cannot be empty!";
    public static final String EMPTY_EVENT_DESCRIPTION = "Task description cannot be empty.";
    public static final String EMPTY_DEADLINE_DESCRIPTION = "Task description cannot be empty!";
    public static final String EVENT_TIME_NOT_SPECIFIED = "Specify /from yyyy-MM-dd and /to yyyy-MM-dd!";
    public static final String EMPTY_START_TIME = "Start time should not be empty!";
    public static final String EMPTY_END_TIME = "End time should not be empty!";
    public static final String DEADLINE_TIME_NOT_SPECIFIED = "Specify xxx /by yyyy-MM-dd!";
    public static final String EMPTY_DUE_DATE = "Due date should not be empty!";
    public static final String INVALID_DATE_FORMAT = "Invalid date format. Please use yyyy-MM-dd.";
    public static final String INVALID_END_DATE = "End date should not be earlier than start date.";
    public static final String EVENT_OVERLAP = "Event duration overlaps with an existing event!";
}