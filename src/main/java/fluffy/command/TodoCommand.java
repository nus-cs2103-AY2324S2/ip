package fluffy.command;

import fluffy.storage.Storage;
import fluffy.task.Todo;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    protected String description;

    /**
     * Constructor for TodoCommand.
     * @param description The description of the todo.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a todo task.
     * @param tasks The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.showTaskAdded(todo, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
