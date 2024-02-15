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
        String[] words= userInput.trim().split("\\s+", 2);
        String commandWord = words[0];
        String taskInfo = words.length > 1 ? words[1] : "";

        CommandType commandType = CommandType.fromString(commandWord);

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(taskInfo);
        case MARK:
            return new MarkCommand(taskInfo);
        case UNMARK:
            return new UnmarkCommand(taskInfo);
        case TODO:
            return new TodoCommand(taskInfo);
        case DEADLINE:
            return new DeadlineCommand(taskInfo);
        case EVENT:
            return new EventCommand(taskInfo);
        case DELETE:
            return new DeleteCommand(taskInfo);
        case INVALID:
        default:
            return new InvalidCommand("Sorry human, but I don't know what that means :-(");
        }
    }
}
