package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.Task;
import blu.task.TaskList;
import blu.ui.UI;

public class DeleteCommand extends Command {
    private final int taskIdx;

    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

     @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        Task task = taskList.getTask(taskIdx);
        taskList.deleteTask(taskIdx);
        ui.showTaskDeleted(task, taskList);
        storage.saveTasks(taskList);
    }
}
