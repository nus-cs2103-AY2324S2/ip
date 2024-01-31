package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     * @param ui      UI interface with the user.
     * @param storage Storage interface for persistence.
     * @return Information about the todo.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(this.todo);
        tasks.add(task);
        return tasks.standardize(task);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TodoCommand otherCommand)) {
            return false;
        }
        return this.todo.equals(otherCommand.todo);
    }
}
