package commands;

import java.io.IOException;
import java.util.ArrayList;
import tasks.Task;

public class ParseCommand {

    public static Command parse(String[] input, ArrayList<Task> tasks) throws IOException {

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
        default:
            return new Command();
        }

    }
}
