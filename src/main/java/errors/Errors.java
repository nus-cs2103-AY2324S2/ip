package errors;

import messages.Messages;

public class Errors {

    // Constants
    public static final String INVALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String INVALID_TASK_NUMBER = "☹ OOPS!!! The task number is invalid.";
    public static final String MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task cannot be empty.";
    public static final String MISSING_DEADLINE_DUE_DATE = "☹ OOPS!!! The due date of a deadline cannot be empty.";
    public static final String MISSING_EVENT_START = "☹ OOPS!!! The start date of an event cannot be empty.";
    public static final String MISSING_EVENT_END = "☹ OOPS!!! The end date of an event cannot be empty.";


    // Errors
    public static final InvalidBanterUsageError InvalidCommandError = new InvalidBanterUsageError(INVALID_COMMAND, Messages.COMMAND_USAGE);
    public static final InvalidBanterUsageError InvalidMarkTaskNumberError = new InvalidBanterUsageError(INVALID_TASK_NUMBER, Messages.MARK_USAGE);
    public static final InvalidBanterUsageError InvalidUnmarkTaskNumberError = new InvalidBanterUsageError(INVALID_TASK_NUMBER, Messages.UNMARK_USAGE);
    public static final InvalidBanterUsageError InvalidDeleteTaskNumberError = new InvalidBanterUsageError(INVALID_TASK_NUMBER, Messages.DELETE_USAGE);
    public static final InvalidBanterUsageError MissingTodoDescriptionError = new InvalidBanterUsageError(MISSING_TASK_DESCRIPTION, Messages.TODO_USAGE);
    public static final InvalidBanterUsageError MissingDeadlineDescriptionError = new InvalidBanterUsageError(MISSING_TASK_DESCRIPTION, Messages.DEADLINE_USAGE);
    public static final InvalidBanterUsageError MissingDeadlineDueDateError = new InvalidBanterUsageError(MISSING_DEADLINE_DUE_DATE, Messages.DEADLINE_USAGE);
    public static final InvalidBanterUsageError MissingEventDescriptionError = new InvalidBanterUsageError(MISSING_TASK_DESCRIPTION, Messages.EVENT_USAGE);
    public static final InvalidBanterUsageError MissingEventStartError = new InvalidBanterUsageError(MISSING_EVENT_START, Messages.EVENT_USAGE);
    public static final InvalidBanterUsageError MissingEventEndError = new InvalidBanterUsageError(MISSING_EVENT_END, Messages.EVENT_USAGE);
}
