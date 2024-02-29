package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.SavingException;
import bob.task.Task;

/**
 * Represents an action to add a todo. An <code>AddTodoCommand</code> object corresponds to
 * a command to add a todo with a specified description.
 */
public class AddTodoCommand extends AddCommand {
    /**
     * Returns a command to add a todo with a specified description.
     *
     * @param description The description for the todo to be added.
     */
    public AddTodoCommand(String description) {
        super(description);
    }

    /**
     * Executes the command to add a todo with a specified description.
     *
     * @param storage The storage to save the new todo in hard disk.
     * @param taskList The task list to store the new todo.
     * @return The response as a result of adding the todo.
     * @throws SavingException If there was an error saving the new todo in hard disk.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws SavingException {
        Task task = taskList.addTodo(description);
        storage.saveTask(task);
        return Ui.getAddResponse(task, taskList.getSize());
    }
}
