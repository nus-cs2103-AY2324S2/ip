package command;

import java.io.IOException;

import exception.BluException;
import exception.IllegalParameterException;
import exception.StorageException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;

public class MarkCommand extends Command {
    private final int taskIdx;

    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws BluException {
        try {
            Task task = taskList.getTask(taskIdx);
            ui.showTaskMarked(task, taskIdx);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParameterException("Failed to mark task. Task number " + taskIdx + " does not exist!\n" 
                                                + "Please use the list command to view task numbers.");
        } catch (IOException e) {
            throw new StorageException("Failed to write to storage file");
        }
    }
    
}
