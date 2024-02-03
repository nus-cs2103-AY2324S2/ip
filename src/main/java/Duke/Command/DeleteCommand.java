package Duke.Command;

import Duke.Exception.InvalidArgumentException;
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
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            // Delete the task
            Task taskToDelete = taskList.getTaskAtPosition(positionToDelete);
            taskList.deleteTaskAtPosition(positionToDelete);
            ui.displayDeletedTask(taskList, taskToDelete);
        } catch (IndexOutOfBoundsException e){
            throw new InvalidArgumentException("The index is out of range >.<");
        }
    }
}