public class Parser {

    public enum Command {
        bye,
        list,
        todo,
        mark,
        unmark,
        deadline,
        event,
        delete,
        invalid
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
            c = Command.invalid;
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
