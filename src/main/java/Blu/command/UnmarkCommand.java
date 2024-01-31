package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.Task;
import blu.task.TaskList;
import blu.ui.UI;

public class UnmarkCommand extends Command {
    private final int taskIdx;

    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }
    
     @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        Task task = taskList.getTask(taskIdx);
        boolean isMarked = task.getIsMarked();
        if (isMarked) {
            task.setUnmarked();
            ui.showTaskUnmarked(task);
        } else {
            ui.showTaskAlreadyUnmarked(taskIdx);
        }
        storage.saveTasks(taskList);
    }
}
