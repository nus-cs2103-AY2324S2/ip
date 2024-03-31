package nihao.enums;

/**
 * Represents a collection of accepted commands.
 */
public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    FIND,
    TODO,
    DEADLINE,
    EVENT,
    UNKNOWN;

    /**
     * Takes in a String and returns the matching command.
     */
    public static Command getEnum(String str) {
        switch (str) {
        case "bye":
            return BYE;
        case "list":
            // Fallthrough
        case "ls":
            return LIST;
        case "mark":
            // Fallthrough
        case "mk":
            return MARK;
        case "unmark":
            // Fallthrough
        case "um":
            return UNMARK;
        case "delete":
            // Fallthrough
        case "del":
            return DELETE;
        case "find":
            // Fallthrough
        case "f":
            return FIND;
        case "todo":
            // Fallthrough
        case "td":
            return TODO;
        case "deadline":
            // Fallthrough
        case "ddl":
            return DEADLINE;
        case "event":
            // Fallthrough
        case "ev":
            return EVENT;
        default:
            return UNKNOWN;
        }
    }
}
