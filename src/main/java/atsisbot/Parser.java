package atsisbot;

/**
 * The Parser class is responsible for parsing user input and extracting the command and arguments.
 */
public class Parser {
    /**
     * Parses the command from the user input.
     *
     * @param input the user input
     * @return the corresponding CommandEnum value
     */
    public static CommandEnum parseCommand(String input) {
        try {
            return CommandEnum.valueOf(input.split(" ")[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandEnum.UNKNOWN;
        }
    }

    /**
     * Parses the arguments from the user input.
     *
     * @param input the user input
     * @return the parsed arguments
     */
    public static String parseArgs(String input) {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length > 1) {
            return splitInput[1];
        } else {
            return "";
        }
    }
}
