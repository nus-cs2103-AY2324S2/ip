/**
 * Enums of all command type.
 */
public enum CommandType {
    TODO, DEADLINE, EVENT, TASK, LIST, LISTCOMMANDS, MARK, UNMARK, DELETE, BYE, FILEPATH, CHECKDATE;

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

        default:
            return null;
        }
    }

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

        case CHECKDATE:
            return "checkdate";

        default:
            return null;
        }
    }



}
