package blu.command;

import java.time.LocalDateTime;

import blu.exception.StorageException;
import blu.storage.Storage;
import blu.task.Event;
import blu.task.TaskList;
import blu.ui.UI;

/**
 * Represents a command to add a new Event task with a specified description and event date/time.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;

    /**
     * Constructs a EventCommand with the specified task description and event start/end date/time.
     *
     * @param description The description of the Event task.
     * @param fromDateTime The event start date and time for the task.
     * @param toDateTime The event end date and time for the task.
     */
    public EventCommand(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.description = description;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Executes the event command.
     * This method creates a new Event task, adds it to the task list, and notify user through UI.
     * The updated task list is saved to storage.
     *
     * @param taskList The TaskList to which the new task is to be added.
     * @param storage The Storage where the updated task list is to be saved.
     * @param ui The UI responsible for user interactions.
     * @return The message to be displayed to the user after adding a event.
     * @throws StorageException If an error occurs during saving to storage.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws StorageException {
        Event event = new Event(description, fromDateTime, toDateTime);
        taskList.addTask(event);
        storage.saveTasks(taskList);
        return ui.showTaskAdded(event, taskList);
    }
}
