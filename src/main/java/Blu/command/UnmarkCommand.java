package Blu.command;

import java.io.IOException;

import Blu.exception.BluException;
import Blu.exception.IllegalParameterException;
import Blu.exception.StorageException;
import Blu.storage.Storage;
import Blu.task.Task;
import Blu.task.TaskList;
import Blu.ui.UI;

public class UnmarkCommand extends Command {
    private final int taskIdx;

    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }
    
     @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        try {
            Task task = taskList.getTask(taskIdx);
            ui.showTaskUnmarked(task, taskIdx);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParameterException("Failed to unmark task. Task number " + taskIdx + " does not exist!\n" 
                                                + "Please use the list command to view task numbers.");
        } catch (IOException e) {
            throw new StorageException("Failed to write to storage file");
        }
    }
}
