package commands;

import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;
import tasks.Task;

/**
 * Deletes a task from the task list.
 */

public class DeleteTaskCommand extends AbstractCommand {
    private int index;
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        try {
            Task task = taskList.deleteTask(this.index);
            storage.saveTasks(taskList);
            return new UserCommand("\tSuccessfully removed task: " + task, taskList.getTaskSummary());
        } catch (DukeException e) {
            System.out.println("\tIndex out of range!");
            return new UserCommand("\tIndex out of range!");
        }
    }
}
