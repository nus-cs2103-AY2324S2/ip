package knight;

/**
 * Represents the different commands that the user can input.
 */
public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    EVENT,
    DEADLINE,
    SAVE,
    FIND,
    UPDATE;

    /**
     * Decide if the command is a task initialisation command.
     *
     * @return True if the command is a task initialisation command.
     */
    boolean isTaskInitialisation() {
        return this == TODO || this == EVENT || this == DEADLINE || this == MARK;
    }
}
