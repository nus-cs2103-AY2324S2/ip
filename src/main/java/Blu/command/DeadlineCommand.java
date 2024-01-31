package blu.command;

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
        storage.saveTasks(taskList);
    }
}
