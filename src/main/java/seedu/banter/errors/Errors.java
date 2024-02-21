package seedu.banter.errors;

import seedu.banter.ui.Ui;


/**
 * Represents a collection of errors that can occur in the application.
 */
public class Errors {

    // Constants
    public static final String INVALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String INVALID_TASK_NUMBER = "☹ OOPS!!! The task number is invalid.";
    public static final String MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task cannot be empty.";
    public static final String MISSING_DEADLINE_DUE_DATE = "☹ OOPS!!! The due date of a deadline cannot be empty.";
    public static final String MISSING_EVENT_START = "☹ OOPS!!! The start date of an event cannot be empty.";
    public static final String MISSING_EVENT_END = "☹ OOPS!!! The end date of an event cannot be empty.";
    public static final String MISSING_KEYWORD = "☹ OOPS!!! The keyword(s) cannot be empty.";


    // Errors
    public static final InvalidBanterUsageError INVALID_COMMAND_ERROR = new InvalidBanterUsageError(
            INVALID_COMMAND, Ui.COMMAND_USAGE);
    public static final InvalidBanterUsageError INVALID_MARK_TASK_NUMBER_ERROR = new InvalidBanterUsageError(
            INVALID_TASK_NUMBER, Ui.MARK_USAGE);
    public static final InvalidBanterUsageError INVALID_UNMARK_TASK_NUMBER_ERROR = new InvalidBanterUsageError(
            INVALID_TASK_NUMBER, Ui.UNMARK_USAGE);
    public static final InvalidBanterUsageError INVALID_DELETE_TASK_NUMBER_ERROR = new InvalidBanterUsageError(
            INVALID_TASK_NUMBER, Ui.DELETE_USAGE);
    public static final InvalidBanterUsageError MISSING_TODO_DESCRIPTION_ERROR = new InvalidBanterUsageError(
            MISSING_TASK_DESCRIPTION, Ui.TODO_USAGE);
    public static final InvalidBanterUsageError MISSING_DEADLINE_DESCRIPTION_ERROR = new InvalidBanterUsageError(
            MISSING_TASK_DESCRIPTION, Ui.DEADLINE_USAGE);
    public static final InvalidBanterUsageError MISSING_DEADLINE_DUE_DATE_ERROR = new InvalidBanterUsageError(
            MISSING_DEADLINE_DUE_DATE, Ui.DEADLINE_USAGE);
    public static final InvalidBanterUsageError MISSING_EVENT_DESCRIPTION_ERROR = new InvalidBanterUsageError(
            MISSING_TASK_DESCRIPTION, Ui.EVENT_USAGE);
    public static final InvalidBanterUsageError MISSING_EVENT_START_ERROR = new InvalidBanterUsageError(
            MISSING_EVENT_START, Ui.EVENT_USAGE);
    public static final InvalidBanterUsageError MISSING_EVENT_END_ERROR = new InvalidBanterUsageError(
            MISSING_EVENT_END, Ui.EVENT_USAGE);
    public static final InvalidBanterUsageError MISSING_KEYWORD_ERROR = new InvalidBanterUsageError(
            MISSING_KEYWORD, Ui.FIND_USAGE);
}
