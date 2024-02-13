package toothless;

import toothless.commands.ByeCommand;
import toothless.commands.Command;
import toothless.commands.DeadlineCommand;
import toothless.commands.DeleteCommand;
import toothless.commands.EventCommand;
import toothless.commands.FindCommand;
import toothless.commands.ListCommand;
import toothless.commands.MarkCommand;
import toothless.commands.TodoCommand;
import toothless.commands.UnmarkCommand;

/**
 * This class is responsible for interpreting the user's inputs and converting them
 * into specific Commands that can be executed within the application.
 * This class acts as a bridge between the user interface and the application's command execution logic,
 * ensuring that user inputs are understood and acted upon appropriately.
 */
public class Parser {

    /**
     * Parses the given input string to produce a corresponding Command object that represents
     * a specific operation or action to be performed by the application.
     * This method identifies the type of command along with any necessary arguments by the user.
     * @param input the raw input string provided by the user, which includes the command keyword and any arguments
     * @return Command corresponding to the user's request
     * @throws ToothlessException if the input is invalid or no Command is suitable
     */
    public static Command parseCommand(String input) throws ToothlessException {
        String[] split = input.split(" ", 2);
        switch (split[0].toUpperCase()) {
        case "BYE":
            return new ByeCommand();
        case "LIST":
            return new ListCommand();
        case "FIND":
            if (split.length < 2) {
                throw new ToothlessException("Input keyword pls");
            }
            return new FindCommand(split[1]);
        case "MARK":
            if (split.length < 2) {
                throw new ToothlessException("Input number pls");
            }
            return new MarkCommand(split[1]);
        case "UNMARK":
            if (split.length < 2) {
                throw new ToothlessException("Input number pls");
            }
            return new UnmarkCommand(split[1]);
        case "DELETE":
            if (split.length < 2) {
                throw new ToothlessException("Input number pls");
            }
            return new DeleteCommand(split[1]);
        case "TODO":
            if (split.length < 2) {
                throw new ToothlessException("Input description @_@");
            }
            return new TodoCommand(split[1]);
        case "EVENT":
            if (split.length < 2) {
                throw new ToothlessException("Input description @_@");
            }
            return new EventCommand(split[1]);
        case "DEADLINE":
            if (split.length < 2) {
                throw new ToothlessException("Input description @_@");
            }
            return new DeadlineCommand(split[1]);
        default:
            assert false : split[0].toUpperCase();
            throw new ToothlessException("Me dragon, no understand this action :P");
        }
    }
}
