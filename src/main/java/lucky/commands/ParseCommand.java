package lucky.commands;

import java.io.IOException;

/**
 * The ParseCommand class is responsible for parsing user input and returning the corresponding
 * Command object.
 */
public class ParseCommand {

    /**
     * Takes in user input and a list of tasks, and returns the corresponding command
     * object based on the input.
     *
     * @param input An array of strings representing the user's input. The first element of the array is
     *          the command, and the subsequent elements are any additional arguments or parameters for
     *          the command.
     * @return Command object based on the input command.
     */
    public static Command parse(String[] input) throws IOException {
        assert input != null : "input[] should never be null";

        CommandsEnum command = CommandsEnum.getCommandEnum(input[0]);

        switch (command) {
        case VIEW_LIST:
            return new ViewTaskListCommand();
        case EXIT:
            return new ExitCommand();
        case SET_MARK:
            return new MarkCommand();
        case SET_UNMARK:
            return new UnmarkCommand();
        case INSERT_TODO:
            return new AddToDoCommand();
        case INSERT_DEADLINE:
            return new AddDeadlineCommand();
        case INSERT_EVENT:
            return new AddEventCommand();
        case DELETE_TASK:
            return new DeleteCommand();
        case FIND:
            return new FindCommand();
        case ADD_TAG:
            return new AddTagCommand();
        case REMOVE_TAG:
            return new RemoveTagCommand();
        default:
            return new Command();
        }
    }
}
