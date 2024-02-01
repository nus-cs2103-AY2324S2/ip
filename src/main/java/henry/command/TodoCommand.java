package henry.command;

import henry.Storage;
import henry.TaskList;
import henry.Ui;
import henry.HenryException;
import henry.task.Todo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String args) throws HenryException {
        if (args.isBlank()) {
            throw new HenryException("No description provided");
        }
        this.description = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        tasks.addTask(new Todo(description));
        storage.save(tasks);
    }
}
