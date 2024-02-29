package duke;

import java.util.HashMap;
import java.util.Map;

public class Commands {
    // Enumerated type for the different commands
    public enum Command {
        BYE, LIST, HELP, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, EMPTY, INVALID
    }

    public static final Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("bye", Command.BYE);
        commandMap.put("list", Command.LIST);
        commandMap.put("help", Command.HELP);
        commandMap.put("mark", Command.MARK);
        commandMap.put("unmark", Command.UNMARK);
        commandMap.put("delete", Command.DELETE);
        commandMap.put("todo", Command.TODO);
        commandMap.put("deadline", Command.DEADLINE);
        commandMap.put("event", Command.EVENT);
        commandMap.put("find", Command.FIND);
    }

    public static Command getCommand(String command) {
        return commandMap.getOrDefault(command, Command.INVALID);
    }
}