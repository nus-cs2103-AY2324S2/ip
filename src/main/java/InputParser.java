/**
 * Parses the input of a ChatBot into argument list.
 *
 * @author Titus Chew
 */
public class InputParser {
    /**
     * Parse the input string into it's command and arguments.
     * @param input the console input
     * @return an action containing the command and it's arguments
     */
    public static Action parseInput(String input) {
        String command = input.trim().split(" ")[0];
        if (command.equals(Command.BYE.name)) {
            return new Action(Command.BYE);
        } else if (command.equals(Command.LIST.name)) {
            return new Action(Command.LIST);
        } else if (command.equals(Command.MARK.name)) {
            return new Action(Command.MARK, parseArguments(input));
        } else if (command.equals(Command.UNMARK.name)) {
            return new Action(Command.UNMARK, parseArguments(input));
        } else if (command.equals(Command.ADD_TODO.name)) {
            return new Action(Command.ADD_TODO, parseArguments(input));
        } else if (command.equals(Command.ADD_DEADLINE.name)) {
            return new Action(Command.ADD_DEADLINE, parseArguments(input));
        } else if (command.equals(Command.ADD_EVENT.name)) {
            return new Action(Command.ADD_EVENT, parseArguments(input));
        } else {
            // The command is invalid, as it is not one of the above commands.
            return new Action(Command.INVALID);
        }
    }

    /**
     * Parse the argument(s) from commands with arguments
     * @param input the console input
     * @return the argument(s)
     */
    private static Argument[] parseArguments(String input) {
        // Split input by arguments
        String[] argString = input.split("/");
        Argument[] args = new Argument[argString.length];

        // identify default argument
        args[0] = parseArgument(argString[0], true);

        // identify additional arguments
        for (int i = 1; i < argString.length; i++) {
            args[i] = parseArgument(argString[i], false);
        }

        return args;
    }

    /**
     * An argument consists of the argument name and value.
     * @param argument the string that belongs to that argument
     * @return the argument that is formed
     */
    private static Argument parseArgument(String argument, boolean isDefault) {
        String[] parsedArg = argument.split(" ", 2);
        if (parsedArg.length != 2) {
            // Invalid argument: missing value
            return new Argument(null);
        }

        String name = parsedArg[0].trim(),
                value = parsedArg[1].trim().equals("") ? null : parsedArg[1].trim();

        // Is valid argument
        if (isDefault) {
            return new Argument(value);
        } else {
            return new Argument(name, value);
        }
    }
}
