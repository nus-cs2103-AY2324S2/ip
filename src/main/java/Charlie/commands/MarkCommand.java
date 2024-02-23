package Charlie.commands;

import Charlie.exceptions.CharlieException;
import Charlie.models.Task;
import Charlie.storage.Storage;
import Charlie.storage.TaskList;
import Charlie.ui.Ui;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieException {
        Task taskToBeMarked = taskList.getTasks().get(index - 1);
        taskToBeMarked.markAsDone();
        ui.printOutput("Marked task: '" + taskToBeMarked.getDescription());
        storage.saveTasks(taskList.getTasks());
        isExit = false;
    }
}
