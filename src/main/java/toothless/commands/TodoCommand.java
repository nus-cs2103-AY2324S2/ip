package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Todo;
import toothless.tasks.Task;

/**
 * Represents a command to add a todo task to the task list.
 * The detail provided includes the task description.
 */
public class TodoCommand extends Command {
    private String detail;

    /**
     * Constructs a TodoCommand with the specified detail, which includes the task description.
     * @param detail The task description.
     */
    public TodoCommand(String detail) {
        this.detail = detail;
    }

    /**
     * Executes the command to add a new todo task to the task list. It creates a new todo task with the
     * specified description, adds it to the task list, and displays a message to the user about the addition.
     *
     * @param ui The user interface to interact with.
     * @param taskList The task list where the new task will be added.
     * @param storage The storage system, not directly used by this command.
     * @return false to indicate the application should continue running.
     * @throws ToothlessException If the task description is missing.
     */
    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (detail.equals("")) {
            throw new ToothlessException(ui.showNoTaskNameWarning());
        }

        Task t = new Todo(detail);
        taskList.addTask(t);

        return ui.showAddedTask(t, taskList.size());
    }

    /**
     * Indicates whether the command is an exit command.
     * @return False, as the command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
