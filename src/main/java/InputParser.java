/**
 * Parses the input of a ChatBot into argument list.
 *
 * @author Titus Chew
 */
public class InputParser {
    /**
     * Possible input commands.
     */
    private static final String
            COMMAND_BYE = "bye",
            COMMAND_LIST = "list",
            COMMAND_MARK = "mark",
            COMMAND_UNMARK = "unmark",
            COMMAND_TODO = "todo",
            COMMAND_DEADLINE = "deadline",
            COMMAND_EVENT = "event";

    /**
     * Parse the input string into it's command and arguments.
     * @param input the console input
     * @return an action containing the command and it's arguments
     */
    public static Action parseInput(String input) {
        if (input.startsWith(COMMAND_BYE)) {
            return new Action(Command.BYE);
        } else if (input.startsWith(COMMAND_LIST)) {
            return new Action(Command.LIST);
        } else if (input.startsWith(COMMAND_MARK)) {
            return new Action(Command.MARK, parseArguments(input));
        } else if (input.startsWith(COMMAND_UNMARK)) {
            return new Action(Command.UNMARK, parseArguments(input));
        } else if (input.startsWith(COMMAND_TODO)) {
            return new Action(Command.TODO, parseArguments(input));
        } else if (input.startsWith(COMMAND_DEADLINE)) {
            return new Action(Command.DEADLINE, parseArguments(input));
        } else if (input.startsWith(COMMAND_EVENT)) {
            return new Action(Command.EVENT, parseArguments(input));
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
        String[] parsedArg = argString[0].split(" ", 2);
        args[0] = new Argument(parsedArg[1].trim());

        // identify additional arguments
        for (int i = 1; i < argString.length; i++) {
            parsedArg = argString[i].split(" ", 2);
            args[i] = new Argument(parsedArg[0].trim(), parsedArg[1].trim());
        }

        return args;
    }
}
