package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.data.Todo;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    /** The description of the todo task. */
    private final String description;

    /**
     * Creates a new TodoCommand with the given description.
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task newTask = new Todo(this.description);
        tasks.addTask(newTask);
        return ui.showAddTask(newTask, tasks.getSize());
    }

    /**
     * Returns true if the given object is equal to this command, only for testing purposes.
     * @param obj The object to compare to.
     * @return True if the given object contains the same description, false otherwise.
     */
    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof TodoCommand) {
            TodoCommand other = (TodoCommand) obj;
            return other.description.equals(this.description);
        } else {
            return false;
        }
    }
}
