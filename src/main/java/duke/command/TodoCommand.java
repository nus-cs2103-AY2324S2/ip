package duke.command;

import duke.TaskList;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Representation of a user's *todo* command.
 */
public class TodoCommand extends Command {

    private final String todo;

    /**
     * Constructor.
     *
     * @param todo What the user needs to do.
     */
    public TodoCommand(String todo) {
        this.todo = todo;
    }

    /**
     * Execute this todo.
     *
     * @param tasks   The list of tasks.
     * @return Information about the todo.
     */
    @Override
    public String execute(TaskList tasks) {
        assert tasks != null;
        Task task = new ToDo(this.todo);
        tasks.add(task);
        return tasks.standardize(task);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TodoCommand)) {
            return false;
        }
        TodoCommand otherCommand = (TodoCommand) other;
        return this.todo.equals(otherCommand.todo);
    }
}
