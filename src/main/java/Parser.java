public class Parser {
    public static Command parse(String commandStart) {
        Command c;
        switch (commandStart) {
        case "bye":
            c = Command.BYE;
            break;
        case "list":
            c = Command.LIST;
            break;
        case "mark":
            c = Command.MARK;
            break;
        case "unmark":
            c = Command.UNMARK;
            break;
        case "todo":
            c = Command.TODO;
            break;
        case "deadline":
            c = Command.DEADLINE;
            break;
        case "event":
            c = Command.EVENT;
            break;
        case "delete":
            c = Command.DELETE;
            break;
        default:
            c = Command.UNKNOWN;
            break;
        }
        return c;
    }
}