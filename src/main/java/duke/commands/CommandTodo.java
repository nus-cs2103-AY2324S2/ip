package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Todo;

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
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.add(this.todo);

        storage.saveTasks(tasks);

        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.todo, tasks.size());
    }
}
