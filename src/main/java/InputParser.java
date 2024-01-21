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
            // Add has no command, and its arguments is the whole input
            return new Action(Command.ADD, new Argument(input));
        }
    }

    /**
     * Parse the argument(s) from commands with arguments
     * @param input the console input
     * @return the argument(s)
     */
    private static Argument[] parseArguments(String input) {
        String[] argString = input.split("/");
        Argument[] args = new Argument[argString.length];

        // identify default argument
        args[0] = new Argument(argString[0].split(" ", 2)[1]);

        // identify additional arguments
        for (int i = 1; i < argString.length; i++) {
            String[] parsedArg = argString[i].split(" ", 2);
            args[i] = new Argument(parsedArg[0], parsedArg[1]);
        }

        return args;
    }
}
