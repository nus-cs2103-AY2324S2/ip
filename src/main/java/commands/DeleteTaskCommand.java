package commands;

import commands.Command;
import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Deletes a task from the task list.
 */

public class DeleteTaskCommand extends Command {
    int index;
    public DeleteTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage)  {
        try {
            taskList.deleteTask(this.index);
            storage.saveTasks(taskList);
        } catch (DukeException e) {
            System.out.println("\tIndex out of range!");
        }
    }
}
