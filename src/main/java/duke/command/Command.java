package duke.command;

/**
 * Enumeration of commands that can be executed in the Duke application.
 */
public enum Command {
    BYE {
        @Override
        public boolean isExit() {
            return true;
        }
    },
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    FIND,
    DELETE;

    public boolean isExit() {
        return false;
    }
}


