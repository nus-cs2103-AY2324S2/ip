package ben.commands;

import ben.storage.Storage;
import ben.tasks.Task;
import ben.tasks.TaskList;
import ben.tasks.Todo;
import ben.ui.Ui;

/**
 * Represents a command to add a new todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Creates a TodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }


    /**
     * Executes the TodoCommand by adding a new Todo task to the task list.
     *
     * @param tasks   The task list to which the new task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTodo = new Todo(false, description);
        tasks.addTask(newTodo);

        return ui.showAddedTaskMessage() +
                ui.show(newTodo.toString()) +
                ui.showCurrNoOfTasks(tasks);
    }
}
