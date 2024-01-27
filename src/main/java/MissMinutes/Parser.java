package MissMinutes;

public class Parser {
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
    }
    public static CommandType parseCommand(String input) {
        String trimmed = input.trim();
        String[] split = trimmed.split(" ", 2);
        String command = split[0].toLowerCase();

        switch (command) {
            case "bye":
                return CommandType.BYE;
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "delete":
                return CommandType.DELETE;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            default:
                return CommandType.UNKNOWN;
        }
    }

}
