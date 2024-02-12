package drew.ui;

import drew.command.*;
import drew.exceptions.InsufficientArgumentsException;
import drew.exceptions.UnknownCommandException;
import drew.storage.TaskList;
import drew.task.Deadline;
import drew.task.Event;
import drew.task.Task;
import drew.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
        if (inputLength == 3 && input.substring(0, 3).equalsIgnoreCase("bye")) {
            userCommand = ByeCommand.getByeCommand();
        } else if (inputLength == 4 && input.substring(0, 4).equalsIgnoreCase("list")) {
            userCommand = new ListCommand(input);
        } else if (inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("mark")) {
            userCommand = new MarkCommand(input);
        } else if (inputLength >= 6 && input.substring(0, 6).equalsIgnoreCase("unmark")) {
            userCommand = new UnmarkCommand(input);
        } else if (inputLength >= 6 && input.substring(0, 6).equalsIgnoreCase("delete")) {
            userCommand = new DeleteCommand(input);
        } else if (inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("todo")) {
            userCommand = new TodoCommand(input);
        } else if (inputLength >= 8 && input.substring(0, 8).equalsIgnoreCase("deadline")) {
            userCommand = new DeadlineCommand(input);
        } else if (inputLength >= 5 && input.substring(0, 5).equalsIgnoreCase("event")) {
            userCommand = new EventCommand(input);
        } else if (inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("find")){
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
