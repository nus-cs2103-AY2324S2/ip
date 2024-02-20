package blu.command;

import blu.exception.StorageException;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.task.ToDo;
import blu.ui.UI;

/**
 * Represents a command to add a new ToDo task with a specified description and event date/time.
 */
public class ToDoCommand extends Command {
    private final String description;

    /**
     * Constructs a ToDoCommand with the specified task description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command.
     * This method creates a new ToDo task, adds it to the task list, and notify user through UI.
     * The updated task list is saved to storage.
     *
     * @param taskList The TaskList to which the new task is to be added.
     * @param storage The Storage where the updated task list is to be saved.
     * @param ui The UI responsible for user interactions.
     * @return The message to be displayed to the user after adding a ToDo task.
     * @throws StorageException If an error occurs during saving to storage.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, UI ui) throws StorageException {
        ToDo toDo = new ToDo(description);
        taskList.addTask(toDo);
        storage.saveTasks(taskList);
        return ui.showTaskAdded(toDo, taskList);
    }
}
