package duke.command;

/**
 * Enums of all command type.
 */
public enum CommandType {
    LISTCOMMANDS, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, CHECKDATE, FIND, UNDO, BYE, FILEPATH,
    SECONDARYFILEPATH, TASK;

    /**
     * Returns Command line of respective enums.
     *
     * @return Command line of respective enums.
     */
    public String getCommand() {
        switch(this) {
        case TODO:
            return "todo [your task]";

        case DEADLINE:
            return "deadline [your task] /by [yyyy-mm-dd hh:mm]";

        case EVENT:
            return "event [your task] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]";

        case LIST:
            return "list";

        case LISTCOMMANDS:
            return "list commands";

        case MARK:
            return "mark [task number (integer)]";

        case UNMARK:
            return "unmark [task number (integer)]";

        case DELETE:
            return "delete [task number (integer)]";

        case BYE:
            return "bye";

        case CHECKDATE:
            return "checkdate [yyyy-mm-dd]";

        case FIND:
            return "find [word]";

        case UNDO:
            return "undo";

        default:
            return null;
        }
    }

    /**
     * Returns String representation of respective enums.
     *
     * @return String representation of respective enums.
     */
    @Override
    public String toString() {
        switch(this) {
        case TODO:
            return "todo";

        case DEADLINE:
            return "deadline";

        case EVENT:
            return "event";

        case TASK:
            return "task";

        case LIST:
            return "list";

        case LISTCOMMANDS:
            return "list commands";

        case MARK:
            return "mark";

        case UNMARK:
            return "unmark";

        case DELETE:
            return "delete";

        case BYE:
            return "bye";

        case FILEPATH:
            return "storage.txt";

        case SECONDARYFILEPATH:
            return "secondary_storage.txt";

        case CHECKDATE:
            return "checkdate";

        case FIND:
            return "find";

        case UNDO:
            return "undo";

        default:
            return null;
        }
    }



}
