package nihao.enums;

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
    public static Command getEnum(String str) {
        switch (str) {
        case "bye":
            return BYE;
        case "list":
            return LIST;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "delete":
            return DELETE;
        case "find":
            return FIND;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        default:
            return UNKNOWN;
        }
    }
}
