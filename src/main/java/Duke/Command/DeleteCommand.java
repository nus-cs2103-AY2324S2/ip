package Duke.Command;

import Duke.Storage;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui;

public class DeleteCommand extends Command {
    int positionToDelete;

    public DeleteCommand(String description) {
        this.positionToDelete = Integer.parseInt(description);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {

        // Delete the task
        Task taskToDelete = taskList.getTaskAtPosition(positionToDelete);
        taskList.deleteTaskAtPosition(positionToDelete);
        ui.displayDeletedTask(taskList, taskToDelete);


    }
}