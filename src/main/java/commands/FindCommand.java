package commands;

import exception.FindFormatException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String NO_TASK_FOUND_MESSAGE = "Sorry Uncle cannot find any matching tasks!";
    private static final String TASK_FOUND_MESSAGE = "Uncle found %s tasks!";
    private final String message;

    public FindCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws FindFormatException {
        if (message.isEmpty()) {
            throw new FindFormatException();
        }
        TaskList matchedTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(message)) {
                matchedTasks.addTasks(task);
            }
        }
        if (matchedTasks.isEmpty()) {
            ui.showToUser(NO_TASK_FOUND_MESSAGE);
        } else {
            ui.showList(String.format(TASK_FOUND_MESSAGE, tasks.numTasks()), matchedTasks);
        }
    }
}
