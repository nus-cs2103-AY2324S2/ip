package duke.ui;

/**
 * This enum contains all messages used in the Parser class.
 */
public enum Messages {
    EXIT("Aw, see you soon!\n"),
    INDEX_ERROR("I can't do that.. Task index %s is out of range!"),

    DELETE_ERROR("Hey, you need to tell me which one to delete! Try 'delete <INDEX>.'"),
    DELETE_INVALID_INDEX_ERROR("Sigh.. That's not a valid number! Try 'delete <NUMBER>'."),
    DELETE_SUCCESS("Deleted task %d"),

    PARSER_DEADLINE_DESCRIPTION_ERROR("Error: Deadline description cannot be empty."),
    PARSER_DEADLINE_DATE_TIME_ERROR("Error: Deadline date/time cannot be empty."),
    PARSER_EVENT_DESCRIPTION_ERROR("Error: Event description cannot be empty."),
    PARSER_EVENT_DATE_TIME_ERROR("Error: Both start and end times are required for the event command. "
            + "Correct format: /from <start> /to <end>"),
    PARSER_VIEW_DATE_ERROR("Please specify a date for viewing tasks!"),
    PARSER_INVALID_DATE_FORMAT("Invalid date format! Please enter the date in YYYY-MM-DD format."),
    PARSER_INVALID_DATE_TIME_FORMAT("Invalid date/time format. Please use yyyy-MM-dd HH:mm format."),
    PARSER_MISSING_KEYWORD_ERROR("Error: Missing %s after command.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
