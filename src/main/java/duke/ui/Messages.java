package duke.ui;

/**
 * This enum contains all messages used in the Parser class.
 */
public enum Messages {
    INDEX_ERROR("I can't do that.. Task index %s is out of range!"),
    INPUT_ERROR("Sorry.. I don't know what that means! Do you need 'help'?"),
    EXIT("Aw, see you soon!"),

    ADD_SUCCESS("Got it! I've added this task:\n"
        + "- %s\n\nYou have %d task(s) in the list."),

    DELETE_ERROR("Hey, you need to tell me which one to delete! Try 'delete <INDEX>.'"),
    DELETE_INVALID_INDEX_ERROR("Sigh.. That's not a valid number! Try 'delete <NUMBER>'."),
    DELETE_SUCCESS("Deleted task %d"),

    FIND_SUCCESS("Here are the matching tasks in your list:\n"),

    HELP_SUCCESS(
            "Here's all the things I can do for you!\n"
            + "1. todo <description> - Add a reminder to do something!\n"
            + "2. deadline <description> /by <deadline> - Add a reminder.. with a deadline!\n"
            + "3. event <description> /from <start> /to <end> - Add an event to your calendar\n"
            + "4. list - List all your tasks and events\n"
            + "5. viewbydate <date> - List all your tasks and events on a specific date\n"
            + "6. find <keyword> - List all your tasks and events with <keyword>\n"
            + "7. mark <index> - Mark a task as done\n"
            + "8. unmark <index> - Mark a task as undone\n"
            + "9. delete <index> - Delete a task. Warning! I can't restore deleted tasks\n"
            + "10. help - Displays this list of commands\n"
            + "11. bye - Leave.."
    ),

    MARK_NO_INDEX_ERROR("I can't do that.. You need to specify a task index! Try \"mark <index>\".."),
    MARK_INDEX_OOR_ERROR("I can't do that.. Task index %s is out of range!"),
    MARK_TASKMARKED_ERROR("I can't do that.. Task %d is already done!"),
    MARK_SUCCESS("Marked task %d as done: %s\nGood job!"),
    MARK_INVALID_INDEX_ERROR("Sigh.. That's not a valid number! Try 'mark <NUMBER>'."),

    UNMARK_INDEX_OUT_OF_RANGE_ERROR("I can't do that.. Task index %s is out of range!"),
    UNMARK_ALREADY_UNDONE_ERROR("I can't do that.. Task %d is already undone!"),
    UNMARK_INVALID_NUMBER_ERROR("Sigh.. That's not a valid number! Try 'unmark <NUMBER>'."),
    UNMARK_MISSING_INDEX_ERROR("I can't do that.. You need to specify a task index! Try \"mark <index>\".."),
    UNMARK_SUCCESS("Oh no.. Marked task %d as undone: %s"),

    PARSER_DEADLINE_DESCRIPTION_ERROR("Error: Deadline description cannot be empty."),
    PARSER_DEADLINE_DATE_TIME_ERROR("Error: Deadline date/time cannot be empty."),
    PARSER_EVENT_DESCRIPTION_ERROR("Error: Event description cannot be empty."),
    PARSER_EVENT_DATE_TIME_ERROR("Error: Both start and end times are required for the event command. "
            + "Correct format: /from <start> /to <end>"),
    PARSER_VIEW_DATE_ERROR("Please specify a date for viewing tasks!"),
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
