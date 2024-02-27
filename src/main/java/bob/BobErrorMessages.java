package bob;

/**
 * Class that contains constants for error messages.
 */
public class BobErrorMessages {
    public static final String UNKNOWN_COMMAND = "Sorry, I'm not sure what command that is.";
    public static final String INVALID_COMMAND_USAGE = "Incorrect usage of command.";

    public static final String INACCESSIBLE_SAVE_DATA = "An error occurred when trying to access the save file.";
    public static final String CORRUPT_SAVE_DATA = "Save file is corrupt. The application will create a new save file.";

    public static final String INVALID_DATE_FORMAT = "Date specified is of the wrong "
            + "format (expects dd/mm/yyyy i.e 01/01/1990).";
    public static final String INVALID_TIME_FORMAT = "Time specified is of the wrong "
            + "format (expects 4 digit 24-hour format i.e 1800).";

    public static String getValidTaskIdMessage(String taskId) {
        return "The command " + taskId + " requires a valid task ID.";
    }

    public static String getTodoExpectFormatMsg() {
        return "The command " + BobParser.TODO_COMMAND
                + " requires a task description.";
    }

    public static String getDeadlineExpectFormatMsg() {
        return "The command " + BobParser.DEADLINE_COMMAND
                + " requires both a task description and a deadline.";
    }

    public static String getEventExpectFormatMsg() {
        return "The command " + BobParser.EVENT_COMMAND
                + " requires a task description, a start date, and an end date.";
    }

    public static String getSortExpectFormatMsg() {
        return "The command " + BobParser.SORT_COMMAND
                + " requires an option (ascending or descending as asc/desc).";
    }
}
