package alpa.commands;

import alpa.exceptions.AlpaException;

/**
 * The Parser class is responsible for parsing user input and returning the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput the user input to be parsed
     * @return the Command object corresponding to the user input
     * @throws AlpaException if there is an error during parsing
     */
    public static Command parse(String userInput) throws AlpaException {
        String[] words = userInput.trim().split("\\s+", 2);
        String commandWord = words[0];
        String taskInfo = words.length > 1 ? words[1] : "";

        CommandType commandType = CommandType.fromString(commandWord);

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            if (taskInfo.isEmpty()) {
                throw new AlpaException("I don't know what you're trying to find, human!");
            }
            return new FindCommand(taskInfo);
        case MARK:
            if (taskInfo.isEmpty()) {
                throw new AlpaException("I don't know what task you're referring to, human!");
            }
            return new MarkCommand(taskInfo);
        case UNMARK:
            if (taskInfo.isEmpty()) {
                throw new AlpaException("I don't know what task you're referring to, human!");
            }
            return new UnmarkCommand(taskInfo);
        case TODO:
            if (taskInfo.isEmpty()) {
                throw new AlpaException("You need to tell me what your todo is, human!");
            }
            return new TodoCommand(taskInfo);
        case DEADLINE:
            if (taskInfo.isEmpty()) {
                throw new AlpaException("What and when is your deadline, human? I need more information!");
            }
            return new DeadlineCommand(taskInfo);
        case EVENT:
            if (taskInfo.isEmpty()) {
                throw new AlpaException("What and when is your event, human? I need more information!");
            }
            return new EventCommand(taskInfo);
        case DELETE:
            if (taskInfo.isEmpty()) {
                throw new AlpaException("I don't know what you're trying to delete, human!");
            }
            return new DeleteCommand(taskInfo);
        case INVALID:
        default:
            return new InvalidCommand("Sorry human, but I don't know what that means :-(");
        }
    }
}
