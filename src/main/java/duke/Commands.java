package duke;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to map string commands to their corresponding Command enum
 * values.
 */
public class Commands {
    /**
     * Enumerated type for the different commands.
     */
    public enum Command {
        BYE, LIST, HELP, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, EMPTY, INVALID, EXIT
    }

    /**
     * A map to associate string commands with their corresponding Command enum
     * values.
     */
    private static final Map<String, Command> commandMap = new HashMap<>();

    /*
     * Static block to initialize the commandMap.
     * Populates the commandMap with their alias and shorthand alias.
     */
    static {
        commandMap.put("b", Command.BYE);
        commandMap.put("bye", Command.BYE);

        commandMap.put("l", Command.LIST);
        commandMap.put("list", Command.LIST);

        commandMap.put("h", Command.HELP);
        commandMap.put("help", Command.HELP);

        commandMap.put("m", Command.MARK);
        commandMap.put("mark", Command.MARK);

        commandMap.put("um", Command.UNMARK);
        commandMap.put("unmark", Command.UNMARK);

        commandMap.put("del", Command.DELETE);
        commandMap.put("delete", Command.DELETE);

        commandMap.put("t", Command.TODO);
        commandMap.put("todo", Command.TODO);

        commandMap.put("d", Command.DEADLINE);
        commandMap.put("deadline", Command.DEADLINE);

        commandMap.put("e", Command.EVENT);
        commandMap.put("event", Command.EVENT);

        commandMap.put("f", Command.FIND);
        commandMap.put("find", Command.FIND);

        commandMap.put("", Command.EMPTY);

        commandMap.put("exit", Command.EXIT);
        commandMap.put("quit", Command.EXIT);
        commandMap.put("q", Command.EXIT);
    }

    /**
     * Returns the Command enum value associated with the given string command.
     * If the command is not recognized, returns Command.INVALID.
     *
     * @param command The string command to look up.
     * @return The Command enum value associated with the given string command.
     */
    public static Command getCommand(String command) {
        return commandMap.getOrDefault(command.toLowerCase(), Command.INVALID);
    }
}