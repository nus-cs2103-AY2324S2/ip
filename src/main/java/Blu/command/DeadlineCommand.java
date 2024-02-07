package blu.command;

import java.time.LocalDateTime;

import blu.exception.StorageException;
import blu.storage.Storage;
import blu.task.Deadline;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command to add a new Deadline task with a specified description and deadline date/time.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime byDateTime;

    /**
     * Constructs a DeadlineCommand with the specified task description and deadline date/time.
     *
     * @param description The description of the Deadline task.
     * @param byDateTime The deadline date and time for the task.
     */
    public DeadlineCommand(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    /**
     * Executes the deadline command.
     * This method creates a new Deadline task, adds it to the task list, and notify user through UI.
     * The updated task list is saved to storage.
     *
     * @param taskList The TaskList to which the new task is to be added.
     * @param storage The Storage where the updated task list is to be saved.
     * @param ui The UI responsible for user interactions.
     * @return The message to be displayed to the user after adding a deadline.
     * @throws StorageException If an error occurs during saving to storage.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws StorageException {
        Deadline deadline = new Deadline(description, byDateTime);
        taskList.addTask(deadline);
        storage.saveTasks(taskList);
        return ui.showTaskAdded(deadline, taskList);
    }
}
