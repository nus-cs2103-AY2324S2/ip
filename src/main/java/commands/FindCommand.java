package commands;

import exception.FindFormatException;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Command representing the 'find' operation in Duke.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String NO_TASK_FOUND_MESSAGE = "Sorry Uncle cannot find any matching tasks!";
    private static final String TASK_FOUND_MESSAGE = "Uncle found %s tasks!";
    private final String message;

    public FindCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws FindFormatException {
        if (message.isEmpty()) {
            throw new FindFormatException();
        }
        TaskList matchedTasks = new TaskList();
        if (matchedTasks.isEmpty()) {
            return NO_TASK_FOUND_MESSAGE;
        }
        for (Task task : tasks) {
            if (task.getDescription().contains(message)) {
                matchedTasks.addTasks(task);
            }
        }
        return String.format(TASK_FOUND_MESSAGE, tasks.numTasks()) + "\n" + matchedTasks;
    }
}
