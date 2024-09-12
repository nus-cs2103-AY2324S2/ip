package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;

/**
 * Class to run the List Command.
 *
 * @author KohGuanZeh
 */
public class ListCommand extends Command {
    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        return taskList.listTasks();
    }
}
