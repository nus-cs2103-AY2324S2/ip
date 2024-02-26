package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.tasks.TaskList;
import alpa.tasks.ToDo;
import alpa.utils.Storage;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand implements Command {
    private final String description;

    /**
     * Constructs a TodoCommand object with the given description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by creating a new todo task, adding it to the task list,
     * displaying the added task, and saving the updated task list to storage.
     *
     * @param taskList The task list.
     * @param storage The storage.
     * @return a message indicating the successful addition of the todo task.
     * @throws AlpaException If the description is empty.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) throws AlpaException {
        if (description.isEmpty()) {
            throw new AlpaException("\nBaa-ad news, human! The description of a todo cannot be empty.");
        }
        ToDo todo = new ToDo(description);
        taskList.addTask(todo);
        storage.saveTasks(taskList.getTasks());
        int size = taskList.getSize();
        return String.format("You added a task human!\n  %s\nNow you have %d tasks in your list!", todo, size);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
