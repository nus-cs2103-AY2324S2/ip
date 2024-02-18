package cvb.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import cvb.exceptions.ConvoBotException;

/**
 * Provides utility methods for parsing commands from user input.
 */
public class CommandParser {

    private static String getAllCommands() {
        // use java steams to concat all commands into a string
        return Arrays.stream(CommandType.values())
                     .map(Enum::name)
                     .collect(Collectors.joining(", "));
    }

    private static CommandType getCommandType(String commandIdentifier) throws ConvoBotException {
        try {
            return CommandType.valueOf(commandIdentifier.toUpperCase());
        } catch (IllegalArgumentException e) {
            String commands = getAllCommands();
            throw new ConvoBotException("Invalid input. Input must be one of: " + commands + ".");
        }
    }

    /**
     * Parses user input and returns the corresponding {@code Command} object.
     *
     * @param userInput the user input to be parsed
     * @return the corresponding {@code Command} object
     * @throws ConvoBotException if the input is invalid or cannot be parsed
     */
    public static Command parse(String userInput) throws ConvoBotException {
        userInput = userInput.trim(); // remove leading and trailing spaces
        ArrayList<String> args = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        if (args.isEmpty()) {
            throw new ConvoBotException("Invalid input. Input must not be empty.");
        }

        CommandType commandType = getCommandType(args.get(0));
        return commandType.getCommand(args);
    }

}
