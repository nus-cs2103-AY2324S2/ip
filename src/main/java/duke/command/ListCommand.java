package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Class to run the List Command.
 *
 * @author KohGuanZeh
 */
public class ListCommand extends Command {
    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException {
        return taskList.listTasks();
    }
}
