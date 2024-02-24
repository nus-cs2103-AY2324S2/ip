package charlie.commands;

import charlie.exceptions.CharlieException;
import charlie.models.Task;
import charlie.storage.Storage;
import charlie.storage.TaskList;
import charlie.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    /**
     * constructor for DeleteCommand
     * @param index index of list item to be removed
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * executes a delete command, the finds the task to be deleted within taskList, deletes the
     * task and saves the new taskList to storage
     * @param taskList - task list loaded at the start of the program.
     * @param ui       - class responsible for user interface interactions
     * @param storage  - class responsible for adding and loading tasks from and into the file
     * @throws CharlieException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieException {
        Task taskToBeDeleted = taskList.getTasks().get(index - 1);
        taskList.getTasks().remove(index - 1);
        ui.printOutput("Deleted task: '" + taskToBeDeleted.getDescription());
        storage.saveTasks(taskList.getTasks());
        isExit = false;
    }
}
