package duke.parser;

/**
 * The Parser class is responsible for parsing user input into commands and arguments.
 */
public class Parser {

    /**
     * Represents the available commands that the parser can recognize.
     */
    public enum Command {
        BYE,
        LIST,
        TODO,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        DELETE,
        INVALID
    }

    private final Command command;
    private final String argument;
    private String unknownCommand = "";

    /**
     * Constructs a Parser object with the specified user input.
     *
     * @param userInput The user input to be parsed.
     */
    public Parser(String userInput) {
        String[] splitInputs = userInput.split("\\s+");
        Command c;

        try {
            c = Command.valueOf(splitInputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            c = Command.INVALID;
            this.unknownCommand = splitInputs[0];
        }

        if (splitInputs.length == 1) {
            this.argument = "";
        } else {
            this.argument = userInput.substring(splitInputs[0].length() + 1);
        }

        this.command = c;
    }

    /**
     * Returns the parsed command.
     *
     * @return The parsed command.
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Returns the parsed argument.
     *
     * @return The parsed argument.
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * Returns the unknown command if the input is invalid.
     *
     * @return The unknown command.
     */
    public String getUnknownCommand() {
        return this.unknownCommand;
    }
}
