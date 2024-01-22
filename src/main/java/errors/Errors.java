package errors;

public class Errors {

    // String constants
    public static final String INVALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String INVALID_TASK_NUMBER = "☹ OOPS!!! The task number is invalid.";
    public static final String MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task cannot be empty.";
    public static final String MISSING_DEADLINE_DUE_DATE = "☹ OOPS!!! The due date of a deadline cannot be empty.";
    public static final String MISSING_EVENT_START = "☹ OOPS!!! The start date of an event cannot be empty.";
    public static final String MISSING_EVENT_END = "☹ OOPS!!! The end date of an event cannot be empty.";
    public static final String TODO_USAGE = "todo <description>";
    public static final String DEADLINE_USAGE = "deadline <description> /by <due date>";
    public static final String EVENT_USAGE = "event <description> /from <start date> /to <end date>";
    public static final String MARK_USAGE = "mark <task number>";
    public static final String UNMARK_USAGE = "unmark <task number>";
    public static final String DELETE_USAGE = "delete <task number>";
    public static final String COMMAND_USAGE = "Banter only supports the following commands:\n" +
            "bye\n" +
            "list\n" +
            MARK_USAGE + "\n" +
            UNMARK_USAGE + "\n" +
            TODO_USAGE + "\n" +
            DEADLINE_USAGE + "\n" +
            EVENT_USAGE;

    // Errors
    public static final InvalidBanterUsageError InvalidCommandError = new InvalidBanterUsageError(INVALID_COMMAND, COMMAND_USAGE);
    public static final InvalidBanterUsageError InvalidMarkTaskNumberError = new InvalidBanterUsageError(INVALID_TASK_NUMBER, MARK_USAGE);
    public static final InvalidBanterUsageError InvalidUnmarkTaskNumberError = new InvalidBanterUsageError(INVALID_TASK_NUMBER, UNMARK_USAGE);
    public static final InvalidBanterUsageError InvalidDeleteTaskNumberError = new InvalidBanterUsageError(INVALID_TASK_NUMBER, DELETE_USAGE);
    public static final InvalidBanterUsageError MissingTodoDescriptionError = new InvalidBanterUsageError(MISSING_TASK_DESCRIPTION, TODO_USAGE);
    public static final InvalidBanterUsageError MissingDeadlineDescriptionError = new InvalidBanterUsageError(MISSING_TASK_DESCRIPTION, DEADLINE_USAGE);
    public static final InvalidBanterUsageError MissingDeadlineDueDateError = new InvalidBanterUsageError(MISSING_DEADLINE_DUE_DATE, DEADLINE_USAGE);
    public static final InvalidBanterUsageError MissingEventDescriptionError = new InvalidBanterUsageError(MISSING_TASK_DESCRIPTION, EVENT_USAGE);
    public static final InvalidBanterUsageError MissingEventStartError = new InvalidBanterUsageError(MISSING_EVENT_START, EVENT_USAGE);
    public static final InvalidBanterUsageError MissingEventEndError = new InvalidBanterUsageError(MISSING_EVENT_END, EVENT_USAGE);
}
