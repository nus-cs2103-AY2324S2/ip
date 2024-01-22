import java.util.HashMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Parser {
    private static HashMap<String, CommandBuilder> registeredCommands = new HashMap<>();

    /**
     * This method registers a command with the parser.
     * It must be called exactly once at program start, which is done by
     * Commands.registerCommands().
     */
    public static void registerCommand(String commandName, CommandBuilder commandBuilder) {
        registeredCommands.put(commandName, commandBuilder);
    }

    public Parser() {
    }

    public Command parse(String command) throws InvalidCommandException {
        command = command.trim();
        String[] parts = command.split(" ", 2);
        if (parts.length == 0) {
            throw new InvalidCommandException("Empty command");
        }
        String commandName = parts[0];
        CommandBuilder commandBuilder = registeredCommands.get(commandName);
        if (commandBuilder == null) {
            throw new InvalidCommandException("Unknown command: " + commandName);
        }
        return commandBuilder.build(parts.length == 1 ? "" : parts[1]);
    }
}
