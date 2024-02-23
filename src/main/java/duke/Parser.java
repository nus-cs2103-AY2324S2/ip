package duke;

import java.util.HashMap;

/**
 * Parser class to parse user commands.
 */
public class Parser {
    private static HashMap<String, CommandBuilder> registeredCommands = new HashMap<>();

    /**
     * Registers a command with the parser.
     * It must be called at least once at program start, which is done by
     * {@link Commands#registerCommands}.
     *
     * @param commandName the name of the command
     * @param commandBuilder the {@link CommandBuilder} object
     */
    public static void registerCommand(String commandName, CommandBuilder commandBuilder) {
        registeredCommands.put(commandName, commandBuilder);
    }

    /**
     * Parses a command.
     *
     * @param command The command to parse.
     * @return the parsed command.
     * @throws InvalidCommandException if the command is invalid.
     */
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
