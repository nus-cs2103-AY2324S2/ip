import action.*;
import action.exception.ActionException;
import action.exception.UnrecognizedCommandException;
import action.util.Argument;
import action.util.Command;

/**
 * Parses the input of a ChatBot into argument list.
 *
 * @author Titus Chew
 */
public class InputParser {
    /**
     * Parse the input string into it's command and arguments.
     *
     * @param input the console input
     * @return an action containing the command and it's arguments
     */
    public static Action parseInput(String input) throws ActionException {
        String command = input.trim().split(" ")[0];
        Argument[] parsedArguments = parseArguments(input);
        if (command.equals(Command.BYE.name)) {
            return new ByeAction(parsedArguments);
        } else if (command.equals(Command.LIST.name)) {
            return new ListAction(parsedArguments);
        } else if (command.equals(Command.MARK.name)) {
            return new MarkAction(parsedArguments);
        } else if (command.equals(Command.UNMARK.name)) {
            return new UnmarkAction(parsedArguments);
        } else if (command.equals(Command.ADD_TODO.name)) {
            return new AddTodoAction(parsedArguments);
        } else if (command.equals(Command.ADD_DEADLINE.name)) {
            return new AddDeadlineAction(parsedArguments);
        } else if (command.equals(Command.ADD_EVENT.name)) {
            return new AddEventAction(parsedArguments);
        } else if (command.equals(Command.DELETE.name)) {
            return new DeleteAction(parsedArguments);
        }else {
            // The command is invalid, as it is not one of the above commands.
            throw new UnrecognizedCommandException(command);
        }
    }

    /**
     * Parse the argument(s) from an input string.
     * <p>
     * "/" is a special character, when in the command, it denotes the start of an argument.
     *
     * @param input the console input
     * @return the parsed argument list
     */
    private static Argument[] parseArguments(String input) {
        // Split input by arguments
        String[] argString = input.split("/");
        Argument[] args = new Argument[argString.length];

        // identify default argument
        args[0] = parseArgument(argString[0]);

        // identify additional arguments
        for (int i = 1; i < argString.length; i++) {
            args[i] = parseArgument(argString[i]);
        }

        return args;
    }

    /**
     * An argument consists of the argument name and value.
     * @param argument the string that belongs to that argument.
     * @return the argument that is formed.
     */
    private static Argument parseArgument(String argument) {
        String[] parsedArg = argument.split(" ", 2);

        // parsedArg will contain at least a name
        String name = parsedArg[0].trim();
        if (parsedArg.length == 1) {
            // Invalid argument: missing value
            return new Argument(parsedArg[0].trim(), null);
        }

        String value = parsedArg[1].trim().equals("") ? null : parsedArg[1].trim();

        return new Argument(name, value);
    }
}
