package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;

/**
 * Class to run Bye Command.
 *
 * @author KohGuanZeh
 */
public class ByeCommand extends Command {
    public static final String BYE_MESSAGE = "Bye. See you again.";

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        return ByeCommand.BYE_MESSAGE;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
