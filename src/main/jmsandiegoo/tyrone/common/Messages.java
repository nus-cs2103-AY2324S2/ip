package jmsandiegoo.tyrone.common;

public class Messages {
    /* General messages */
    public static String MESSAGE_GREET = "Yo, what's crackin' fam! I'm Tyrone, your digital homie.\n\n"
            + "What's the word? So I can help you out today.";

    public static String MESSAGE_ADD_TASK = "Got it added homie:\n\n%1s\n"
            + "Now you have %2d in the list.";

    /* General Exception messages */
    public static String MESSAGE_NOT_EXIST_CMD = "Command entered doesn't exist.";

    public static String MESSAGE_INCORRECT_COMMAND_FORMAT = "Your %1s command has incorrect format.\n"
            + "Gotta follow the groove: %2s";
    public static String MESSAGE_INCORRECT_COMMAND_INDEX = "Your %1s id is acting up.\n"
            + "Double check that: 1 <= id <= task list size";
    public static String MESSAGE_INITIALIZE_FAIL = "Hey fam, I messed up my system initialization. Exiting...";
    public static String MESSAGE_ERROR = "Hey homie, we've got a hiccup...\n\n%1s";
    public static String MESSAGE_BYE = "Peace out! Crossin' my fingers for a speedy reunion, ya feel?";

    /* List command messages */
    public static String MESSAGE_LIST = "Peep the lineup, here's the rundown of tasks on your list:\n%1s";

    /* Todo command messages */
    public static String MESSAGE_TODO_EMPTY_DESC = "Can't leave that to-do description hanging dry. "
            + "Gotta drop some words in there!";
    public static String MESSAGE_TODO_UNDO = "Todo command undone.";

    /* Deadline command messages */
    public static String MESSAGE_DEADLINE_INCORRECT = String.format(
            MESSAGE_INCORRECT_COMMAND_FORMAT,
            "deadline",
            "\"deadline <task description> /by <yyyy-mm-dd[ HH:mm]>\""
    );
    public static String MESSAGE_DEADLINE_UNDO = "Deadline command undone.";

    /* Event command messages */
    public static String MESSAGE_EVENT_INCORRECT = String.format(
            MESSAGE_INCORRECT_COMMAND_FORMAT,
            "event",
            "\"event <task description> /from <yyyy-mm-dd[ HH:mm]> /to <yyyy-mm-dd[ HH:mm]>\""
    );
    public static String MESSAGE_EVENT_UNDO = "Event command undone.";

    /* Mark command messages */
    public static String MESSAGE_MARK = "Dope! Check it, I've tagged this task as handled:\n%1s";
    public static String MESSAGE_MARK_UNDO = "Mark command undone.";

    /* Unmark command messages */
    public static String MESSAGE_UNMARK = "A'ight, I've stamped this task as still in the works:\n%1s";

    public static String MESSAGE_UNMARK_UNDO = "Unmark command undone.";
    /* Delete command messages */
    public static String MESSAGE_DELETE = "Boom! Task officially evicted from the list. Consider it gone:\n%1s\n"
            + "Now you have %2d in the list.";
    public static String MESSAGE_DELETE_UNDO = "Delete command undone.";

    /* Find command messages */
    public static String MESSAGE_FIND = "Yo, check it out, here's the lineup of tasks that match up:\n%1s";
    public static String MESSAGE_FIND_INCORRECT = String.format(
            MESSAGE_INCORRECT_COMMAND_FORMAT,
            "find",
            "\"find <keyword>\""
    );

    /* Undo command messages */
    public static String MESSAGE_UNDO = "Undo command detected: \n";
}
