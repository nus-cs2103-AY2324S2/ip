package henry.command;

import henry.HenryException;
import henry.Storage;
import henry.TaskList;
import henry.task.Todo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Creates a TodoCommand object.
     *
     * @param args The arguments of the command.
     * @throws HenryException If the command is invalid.
     */
    public TodoCommand(String args) throws HenryException {
        if (args.isBlank()) {
            throw new HenryException("No description provided");
        }
        this.description = args;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws HenryException {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        storage.save(tasks);
        return String.format("Added this task\n%s\nNow you have %d tasks in the list :(\n",
                newTodo,
                tasks.getNumOfTasks());
    }
}
