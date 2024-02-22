package seedu.banter.ui;

import seedu.banter.enums.CommandType;
import seedu.banter.utilities.DateTime;


/**
 * Represents the user interface of the application.
 */
public class Ui {
    public static final String TODO_USAGE = "todo <description>";
    public static final String DEADLINE_USAGE = "deadline <description> /by <due date (time)>";
    public static final String EVENT_USAGE = "event <description> /from <start date (time)> /to <end date (time)>";
    public static final String MARK_USAGE = "mark <task number>";
    public static final String UNMARK_USAGE = "unmark <task number>";
    public static final String DELETE_USAGE = "delete <task number>";
    public static final String FIND_USAGE = "find <keyword(s)>";
    public static final String EMPTY_LIST = "You have not added any tasks yet!";
    public static final String DATE_TIME_FORMAT = "Accepted date time formats: "
            + DateTime.getAcceptedDateTimeFormats();
    public static final String COMMAND_USAGE = "This is what Banter can do:\n"
            + CommandType.LIST + "\n"
            + MARK_USAGE + "\n"
            + UNMARK_USAGE + "\n"
            + TODO_USAGE + "\n"
            + DEADLINE_USAGE + "\n"
            + EVENT_USAGE + "\n"
            + DELETE_USAGE + "\n"
            + FIND_USAGE + "\n\n"
            + DATE_TIME_FORMAT + "\n";
    public static final String GREET_MESSAGE_BODY = "Hello! I'm Banter\n"
            + "What can I do for you?\n\n" + COMMAND_USAGE;


    // Messages
    public static final Card GREET_MESSAGE = new Card(GREET_MESSAGE_BODY);
}
