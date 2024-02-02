public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    EVENT,
    DEADLINE,
    SAVE;

    boolean isTaskInitialisation() {
        return this == TODO || this == EVENT || this == DEADLINE || this == MARK;
    }
}
