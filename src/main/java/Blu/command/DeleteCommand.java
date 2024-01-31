package blu.command;

import java.io.IOException;

import blu.exception.BluException;
import blu.exception.IllegalParameterException;
import blu.exception.StorageException;
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
         try {
            Task task = taskList.getTask(taskIdx);
            taskList.deleteTask(taskIdx);
            ui.showTaskDeleted(task, taskList);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParameterException("Failed to delete task. Task number " + taskIdx + " does not exist!\n" 
                                                + "Please use the list command to view task numbers.");
        } catch (IOException e) {
            throw new StorageException("Failed to write to storage file");
        }
    }
    
}
