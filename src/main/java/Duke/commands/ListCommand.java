package duke.commands;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class to execute ListCommand
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> currentTasks = tasks.getTasks();
        return ui.displayListMessage(currentTasks);
    }
}
