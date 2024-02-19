package drew.ui;

import drew.command.ByeCommand;
import drew.command.Command;
import drew.command.DeadlineCommand;
import drew.command.DeleteCommand;
import drew.command.EventCommand;
import drew.command.FindCommand;
import drew.command.ListCommand;
import drew.command.MarkCommand;
import drew.command.TodoCommand;
import drew.command.UnknownCommand;
import drew.command.UnmarkCommand;
import drew.exceptions.InsufficientArgumentsException;
import drew.exceptions.UnknownCommandException;

/**
 * This class handles the interpretation and execution of user input.
 */
public class Parser {
    /**
     * Checks the identity of the command.
     *
     * @param input String containing full user input.
     * @return Command specified by user input.
     * @throws UnknownCommandException Command is not recognized.
     * @throws InsufficientArgumentsException Command is supplied with too little arguments.
     * @throws IllegalArgumentException Command is supplied with the wrong arguments.
     */
    public Command checkCommandId(String input) throws UnknownCommandException,
            InsufficientArgumentsException, IllegalArgumentException {

        int inputLength = input.length();
        Command userCommand;

        if (ByeCommand.isByeCommand(inputLength, input)) {
            userCommand = ByeCommand.getByeCommand();
        } else if (ListCommand.isListCommand(inputLength, input)) {
            userCommand = new ListCommand(input);
        } else if (MarkCommand.isMarkCommand(inputLength, input)) {
            userCommand = new MarkCommand(input);
        } else if (UnmarkCommand.isUnmarkCommand(inputLength, input)) {
            userCommand = new UnmarkCommand(input);
        } else if (DeleteCommand.isDeleteCommand(inputLength, input)) {
            userCommand = new DeleteCommand(input);
        } else if (TodoCommand.isTodoCommand(inputLength, input)) {
            userCommand = new TodoCommand(input);
        } else if (DeadlineCommand.isDeadlineCommand(inputLength, input)) {
            userCommand = new DeadlineCommand(input);
        } else if (EventCommand.isEventCommand(inputLength, input)) {
            userCommand = new EventCommand(input);
        } else if (FindCommand.isFindCommand(inputLength, input)) {
            userCommand = new FindCommand(input);
        } else {
            userCommand = UnknownCommand.getUnkownCommand();
        }
        assert !userCommand.equals(null);
        return userCommand;
    }
}
