package liv.processor;

import liv.container.Storage;
import liv.container.TaskList;
import liv.task.TodoTask;
import liv.ui.Ui;

/**
 * Represents a command that add a TodoTask task to the list.
 */
public class TodoCommand extends Command {
    private TodoTask todo;

    /**
     * Constructor of the class.
     * @param todo The TodoTask object to be added.
     */
    public TodoCommand(TodoTask todo) {
        this.todo = todo;
    }

    /**
     * Add the TodoTask object to the list and signals the Ui to respond.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The Ui to gives interaction with users.
     * @param storage The storage where the data is stored.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(todo);
        String message = Ui.getTodoMessage(todo);
        storage.saveTaskToFile();
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChangedData() {
        return true;
    }
}
