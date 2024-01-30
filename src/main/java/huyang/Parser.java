package huyang;

public class Parser {
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, UNKNOWN;
    }

    public CommandType parseCommand(String input) {
        if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark ")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event ")) {
            return CommandType.EVENT;
        } else if (input.startsWith("delete ")) {
            return CommandType.DELETE;
        } else if (input.equals("bye")) {
            return CommandType.BYE;
        } else {
            return CommandType.UNKNOWN;
        }
    }
}
