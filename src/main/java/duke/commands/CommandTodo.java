package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

/**
 * Represents the command for adding a todo task in the Duke application.
 */
public class CommandTodo extends Command {
    private final Todo todo;

    /**
     * Constructs a new CommandTodo object with the specified todo task.
     *
     * @param todo The todo task to be added.
     */
    public CommandTodo(Todo todo) {
        this.todo = todo;
    }

    /**
     * Executes the "todo" command, which adds the specified todo task to the task list.
     *
     * @param tasks   The task list to which the todo task will be added.
     * @param ui      The user interface component for displaying messages to the user.
     * @param storage The storage component for saving the updated task list.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.todo);

        storage.saveTasks(tasks);

        ui.showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.todo, tasks.size()));
    }
}
