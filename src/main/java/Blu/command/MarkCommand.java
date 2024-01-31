package blu.command;

import blu.exception.BluException;
import blu.storage.Storage;
import blu.task.Task;
import blu.task.TaskList;
import blu.ui.UI;

public class MarkCommand extends Command {
    private final int taskIdx;

    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        Task task = taskList.getTask(taskIdx);
        boolean isMarked = task.getIsMarked();
        if (isMarked) {
            ui.showTaskAlreadyMarked(taskIdx);
        } else {
            task.setMarked();
            ui.showTaskMarked(task, taskIdx);
        }
        storage.saveTasks(taskList);
    }
    
}
