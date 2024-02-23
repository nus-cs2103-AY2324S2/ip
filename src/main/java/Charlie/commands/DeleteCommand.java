package Charlie.commands;

import Charlie.exceptions.CharlieException;
import Charlie.models.Task;
import Charlie.storage.Storage;
import Charlie.storage.TaskList;
import Charlie.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieException {
        Task taskToBeDeleted = taskList.getTasks().get(index - 1);
        taskList.getTasks().remove(index-1);
        ui.printOutput("Deleted task: '" + taskToBeDeleted.getDescription());
        storage.saveTasks(taskList.getTasks());
        isExit = false;
    }
}
