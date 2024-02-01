package duke.parser;

public class Parser {

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

    public Parser(String userInput) {
        String[] splitInputs = userInput.split("\\s+");
        Command c;

        try {
            c = Command.valueOf(splitInputs[0].toLowerCase());
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

    public Command getCommand() {
        return this.command;
    }

    public String getArgument() {
        return this.argument;
    }

    public String getUnknownCommand() {
        return this.unknownCommand;
    }
}
