package parser;

import java.util.Arrays;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.RemindCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;

/**
 * The Parser class is responsible for parsing user input and creating corresponding Command objects.
 * It recognizes the command keyword and any accompanying message in the input and constructs the
 * appropriate command object to execute the desired action.
 *
 */
public abstract class Parser {
    /**
     * Takes in the user input and splits it into command and message.
     * Uses a switch statement to determine the specific command type to create.
     *
     * @param userInput User input string.
     * @return Returns the relevant Command based on the user input.
     */
    public static Command parse(String userInput) {
        String[] inputs = userInput.split(" ");
        String command = inputs[0];
        String message = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        switch (command) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(message);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(message);
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(message);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(message);
        case EventCommand.COMMAND_WORD:
            return new EventCommand(message);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(message);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(message);
        case RemindCommand.COMMAND_WORD:
            return new RemindCommand();
        default:
            return new HelpCommand();
        }
    }
}
