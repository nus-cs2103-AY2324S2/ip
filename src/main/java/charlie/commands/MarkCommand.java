package charlie.commands;

import charlie.exceptions.CharlieException;
import charlie.models.Task;
import charlie.storage.Storage;
import charlie.storage.TaskList;
import charlie.ui.Ui;

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
