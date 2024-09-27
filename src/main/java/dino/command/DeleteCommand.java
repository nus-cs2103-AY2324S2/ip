package dino.command;

import dino.DinoException;
import dino.Storage;
import dino.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList taskList, Storage storage) throws DinoException {
        return taskList.deleteTask(this.index);
    }
}
