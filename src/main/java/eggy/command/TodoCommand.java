package eggy.command;

import eggy.exception.EggyException;
import eggy.exception.IncompleteTaskException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Task;
import eggy.task.Todo;
import eggy.ui.Ui;

/**
 * Represents a command to add a todo to the task list.
 */
public class TodoCommand extends Command {
    /** The name of the todo task. */
    private String name;

    /**
     * Constructs a TodoCommand.
     *
     * @param commands The array of commands.
     * @throws IncompleteTaskException If the task description is incomplete.
     */
    public TodoCommand(String[] commands) throws IncompleteTaskException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("todo");
        }
        this.name = commands[1];
    }

    /**
     * Adds a todo to the task list and saves the task list to the storage.
     *
     * @param tasks The task list of the chatbot.
     * @param ui The user interface of the chatbot.
     * @param storage The storage of the chatbot.
     * @throws EggyException If there is an error saving the task list to the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException {
        Task newTodo = new Todo(this.name);
        tasks.addTask(newTodo);
        ui.printTaskAdded(newTodo, tasks.getSize());
        storage.save(tasks);
    }
}
