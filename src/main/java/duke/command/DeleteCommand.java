package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.Task;
import duke.task.TaskList;

import duke.exception.InvalidArgumentException;

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