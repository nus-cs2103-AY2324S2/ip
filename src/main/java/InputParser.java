import action.Action;
import action.Argument;
import action.Command;
import action.ByeAction;
import action.ListAction;
import action.MarkAction;
import action.UnmarkAction;
import action.AddTodoAction;
import action.AddDeadlineAction;
import action.AddEventAction;
import action.InvalidAction;

/**
 * Parses the input of a ChatBot into argument list.
 *
 * @author Titus Chew
 */
public class InputParser {
    /**
     * Parse the input string into it's command and arguments.
     * @param input The console input
     * @return An action containing the command and it's arguments
     */
    public static Action parseInput(String input) {
        String command = input.trim().split(" ")[0];
        if (command.equals(Command.BYE.name)) {
            return new ByeAction();
        } else if (command.equals(Command.LIST.name)) {
            return new ListAction();
        } else if (command.equals(Command.MARK.name)) {
            return new MarkAction(parseArguments(input));
        } else if (command.equals(Command.UNMARK.name)) {
            return new UnmarkAction(parseArguments(input));
        } else if (command.equals(Command.ADD_TODO.name)) {
            return new AddTodoAction(parseArguments(input));
        } else if (command.equals(Command.ADD_DEADLINE.name)) {
            return new AddDeadlineAction(parseArguments(input));
        } else if (command.equals(Command.ADD_EVENT.name)) {
            return new AddEventAction(parseArguments(input));
        } else {
            // The command is invalid, as it is not one of the above commands.
            return new InvalidAction();
        }
    }

    /**
     * Parse the argument(s) from commands with arguments
     * @param input The console input.
     * @return The argument(s).
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
     * @param argument The string that belongs to that argument.
     * @return The argument that is formed.
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
