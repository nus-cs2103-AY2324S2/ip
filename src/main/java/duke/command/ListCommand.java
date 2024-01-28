package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Class to run the List Command.
 *
 * @author KohGuanZeh
 */
public class ListCommand extends Command {
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.listTasks());
    }
}
