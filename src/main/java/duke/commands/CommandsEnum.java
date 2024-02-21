package duke.commands;

/**
 * The type of commands the chatbot is able to execute.
 */
public enum CommandsEnum {
    INSERT_TODO, VIEW_LIST, INSERT_DEADLINE, INSERT_EVENT, SET_MARK, SET_UNMARK, EXIT, DELETE_TASK, FIND,
    ADD_TAG, REMOVE_TAG, DEFAULT;

    /**
     * Takes a string input and returns the corresponding command enum based on the
     * input.
     *
     * @param in The parameter "in" is a string that represents a command input.
     * @return The method is returning a CommandsEnum value.
     */
    public static CommandsEnum getCommandEnum(String in) {
        in = in.toLowerCase();

        switch (in) {
        case "todo":
            return INSERT_TODO;
        case "list":
            return VIEW_LIST;
        case "deadline":
            return INSERT_DEADLINE;
        case "event":
            return INSERT_EVENT;
        case "mark":
            return SET_MARK;
        case "unmark":
            return SET_UNMARK;
        case "bye":
            return EXIT;
        case "delete":
            return DELETE_TASK;
        case "find":
            return FIND;
        case "tag":
            return ADD_TAG;
        case "removetag":
            return REMOVE_TAG;
        default:
            return DEFAULT;
        }
    }
}
