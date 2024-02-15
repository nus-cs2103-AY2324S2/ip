package commands;

/**
 * Contains all the possible command types
 */
public enum Commands {
    HELP,
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    BYE,
    DELETE,
    FIND;

    public String getHelpMessage() {
        switch (this) {
        case HELP:
            return "Get list of commands: help";
        case TODO:
            return "Create todo: todo [task name] (eg: todo borrow book)";
        case DEADLINE:
            return "Create deadline: deadline [task name] /by [due date] "
                    + "(eg: deadline homework /by Sun 6pm)";
        case EVENT:
            return "Create event: event [task name] /from [from date] /to [to date] "
                    + "(eg: event concert /from Mon 6pm /to Mon 8pm)";
        case LIST:
            return "List current tasks: list";
        case MARK:
            return "Mark a task as complete: mark [task number on list] "
                    + "(eg: mark 2)";
        case UNMARK:
            return "Mark a task as incomplete: unmark [task number on list] "
                    + "(eg: unmark 2)";
        case DELETE:
            return "Delete a task: delete [task number on list] "
                    + "(eg: delete 3)";
        case FIND:
            return "Find a task by keyword: find [keyword] "
                    + "(eg: find book)";
        case BYE:
            return "Close Tam the Task Manager: bye";
        default:
            return "getHelpMessage() ERROR";
        }
    }
}
