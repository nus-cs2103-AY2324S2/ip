package drew.ui;

import drew.command.Command;
import drew.exceptions.InsufficientArgumentsException;
import drew.exceptions.UnknownCommandException;

import drew.command.ByeCommand;
import drew.command.DeadlineCommand;
import drew.command.DeleteCommand;
import drew.command.EventCommand;
import drew.command.FindCommand;
import drew.command.ListCommand;
import drew.command.MarkCommand;
import drew.command.TodoCommand;
import drew.command.UnmarkCommand;
import drew.command.UnknownCommand;


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

        String inputNoWhitespace = input.replaceAll("\\s", "");
        int inputNoWhitespaceLength = inputNoWhitespace.length();

//        switch (userCommand) {
//        case UNKNOWN:
//            throw new UnknownCommandException("drew.command.Command not recognized");
//        case LIST:
//            if (inputNoWhitespaceLength != 4) {
//                throw new IllegalArgumentException("No arguments are needed");
//            }
//            break;
//        case TODO:
//            if (inputNoWhitespaceLength == 4) {
//                throw new InsufficientArgumentsException("'Todo task' cannot be empty");
//            }
//            break;
//        case MARK:
//            if (inputNoWhitespaceLength == 4) {
//                throw new InsufficientArgumentsException("'Mark index' cannot be empty");
//            }
//            break;
//        case UNMARK:
//            if (inputNoWhitespaceLength == 6) {
//                throw new InsufficientArgumentsException("'Unmark index' cannot be empty");
//            }
//            break;
//        case DELETE:
//            if (inputNoWhitespaceLength == 6) {
//                throw new InsufficientArgumentsException("'Delete index' cannot be empty");
//            }
//        case DEADLINE:
//            if (inputNoWhitespaceLength == 8) {
//                throw new InsufficientArgumentsException("'Deadline task' cannot be empty");
//            }
//            break;
//        case EVENT:
//            if (inputNoWhitespaceLength == 5) {
//                throw new InsufficientArgumentsException("'Event task' cannot be empty");
//            }
//            break;
//        case FIND:
//            if (inputNoWhitespaceLength == 4) {
//                throw new InsufficientArgumentsException("'Find' String cannot be empty");
//            }
//        }
        return userCommand;
    }
}
