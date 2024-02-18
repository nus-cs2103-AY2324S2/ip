package commands;

import java.io.IOException;

import exception.TodoFormatException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Represents the command used to add a todo to the task list.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private static final String SUCCESS_MESSAGE = "Got it. Uncle added this todo:\n\t %s"
            + "\n Now you have %s task(s) in the list.";

    private final String message;

    /**
     * Creates a new TodoCommand object with the provided message.
     *
     * @param message Input message containing description.
     */
    public TodoCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the TodoCommand, adding a todo task to the task list based on the provided input message.
     * The input message is expected to contain a description.
     *
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     * @throws TodoFormatException Thrown when the input does not have a message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TodoFormatException {
        if (message.isEmpty()) {
            throw new TodoFormatException();
        }
        Task todo = new Todo(message);
        tasks.addTasks(todo);
        try {
            storage.appendToFile(tasks);
        } catch (IOException e) {
            return e.getMessage();
        }
        return String.format(SUCCESS_MESSAGE, todo, tasks.numTasks());
    };
}
