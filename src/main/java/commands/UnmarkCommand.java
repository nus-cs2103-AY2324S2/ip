package commands;

import java.io.IOException;

import storage.Storage;
import task.TaskList;

/**
 * Represents the command used to unmark tasks in the task list as done.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private static final String SUCCESS_MESSAGE = "Nice! Uncle unmarked this task:\n\t %s";
    private final String message;

    /**
     * Creates a new UnmarkCommand object with the provided message.
     * @param message Input message containing index to unmark.
     */
    public UnmarkCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the UnmarkCommand, un-marking a task from the task list based on the input index.
     * If the input index is out of range of the task list, an IndexOutOfBoundsException is thrown.
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            int index = Integer.parseInt(message);
            tasks.get(index - 1).unmark();
            storage.appendToFile(tasks);
            assert tasks.get(index - 1).getStatus().equals("0") : "task should be unmarked";
            return String.format(SUCCESS_MESSAGE, tasks.get(index - 1));
        } catch (IndexOutOfBoundsException | IOException e) {
            return e.getMessage();
        }
    }
}
