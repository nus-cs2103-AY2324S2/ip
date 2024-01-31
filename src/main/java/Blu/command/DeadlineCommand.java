package blu.command;

import java.io.IOException;
import java.time.LocalDateTime;

import blu.exception.StorageException;
import blu.storage.Storage;
import blu.task.Deadline;
import blu.task.TaskList;
import blu.ui.UI;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime byDateTime;

    public DeadlineCommand(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws StorageException {
        Deadline deadline = new Deadline(description, byDateTime);
        taskList.addTask(deadline);
        ui.showTaskAdded(deadline, taskList);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            throw new StorageException("Failed to write to storage file");
        }
    }
}
