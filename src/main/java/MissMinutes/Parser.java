package MissMinutes;

public class Parser {
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
    }
    public static CommandType parseCommand(String command) {
        if (command.toLowerCase().startsWith("bye")) {
            return CommandType.BYE;
        } else if (command.toLowerCase().startsWith("list")) {
            return CommandType.LIST;
        } else if (command.toLowerCase().startsWith("mark")) {
            return CommandType.MARK;
        } else if (command.toLowerCase().startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (command.toLowerCase().startsWith("delete")) {
            return CommandType.DELETE;
        } else if (command.toLowerCase().startsWith("todo")) {
            return CommandType.TODO;
        } else if (command.toLowerCase().startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (command.toLowerCase().startsWith("event")) {
            return CommandType.EVENT;
        } else {
            return CommandType.UNKNOWN;
        }
    }

}
