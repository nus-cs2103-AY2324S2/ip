package arc.commands;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.TaskList;
import arc.tasks.Todo;

/**
 * Represents the command for adding a todo task in the Arc application.
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
    public String execute(TaskList tasks, Storage storage) throws ArcException {
        tasks.add(this.todo);

        storage.saveTasks(tasks);

        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.todo, tasks.size());
    }
}
