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
    FIND,
    REDIRECT;

    public String getHelpMessage() {
        switch (this) {
        case HELP:
            return "Get list of commands: help";
        case TODO:
            return "Create todo: todo [task name] (eg: todo borrow book)";
        case DEADLINE:
            return "Create deadline: deadline [task name] /by [due date in yyyy-mm-dd] "
                    + "(eg: deadline homework /by 2024-10-10)";
        case EVENT:
            return "Create event: event [task name] /from [from date in yyyy-mm-dd] /to [to date in yyyy-mm-dd] "
                    + "(eg: event concert /from 2024-10-10 /to 2024-10-11)";
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
        case REDIRECT:
            return "https://github.com/brennalaurentan/ip/blob/master/docs/HELP.md";
        default:
            return "getHelpMessage() ERROR";
        }
    }
}
