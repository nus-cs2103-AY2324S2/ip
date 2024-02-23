package eggy.command;

import eggy.exception.DuplicatedTaskException;
import eggy.exception.IncompleteTaskException;
import eggy.exception.SaveTasksException;
import eggy.response.Response;
import eggy.storage.Storage;
import eggy.task.Task;
import eggy.task.TaskList;
import eggy.task.Todo;

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
    public TodoCommand(String... commands) throws IncompleteTaskException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("todo");
        }
        this.name = commands[1];
    }

    /**
     * Adds a todo to the task list and saves the task list to the storage.
     *
     * @param tasks The task list of the chatbot.
     * @param response The response of the chatbot.
     * @param storage The storage of the chatbot.
     * @throws SaveTasksException If there is an error saving the task list to the storage.
     * @throws DuplicatedTaskException If the task to be added is already in the task list.
     */
    @Override
    public void execute(TaskList tasks, Response response, Storage storage)
            throws SaveTasksException, DuplicatedTaskException {
        Task newTodo = new Todo(this.name);
        tasks.addTask(newTodo);
        response.setTaskAddedResponse(newTodo, tasks.getSize());
        storage.save(tasks);
    }
}
