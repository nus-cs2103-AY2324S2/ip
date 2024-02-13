package cappy.constant;

/** Represents the error messages that can be shown to the user. */
public class Message {
    public static final String MISSING_DESCRIPTION = "Please enter the task description";
    public static final String MISSING_DUE_DATE =
            "Please specify the due date of the deadline task using /by [Date Time].";
    public static final String MISSING_TO_FROM_DATETIME =
            "Please specify the duration of the event using /from [Date Time] /to [Date Time].";
    public static final String MISSING_KEYWORD = "Please enter a keyword to search for.";
    public static final String MISSING_INDEX = "Please enter an index.";

    public static final String INVALID_DATETIME_FORMAT = "Please use the correct datetime format.";
    public static final String INVALID_INDEX = "Please enter a valid index.";
    public static final String INVALID_COMMAND = "I'm sorry but I don't know what that means :-(";
    public static final String INVALID_STORAGE_FORMAT = "Invalid storage format!";

    public static final String NO_MATCHES_FOUND = "No matches found :(";

    public static final String GREETING = "Hello! I'm Cappy\nWhat can I do for you?";
    public static final String GOODBYE = "Bye! Hope to see you again soon!";
}
