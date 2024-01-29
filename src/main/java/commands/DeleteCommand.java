package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String SUCCESS_MESSAGE = "Uncle deleted this item:\n\t\t %s"
            + "\n\t Now you have %s task(s) in the list.";
    private final String message;

    public DeleteCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int index = Integer.parseInt(message);
            Task removed = tasks.get(index - 1);
            tasks.remove(index - 1);
            ui.showToUser(String.format(SUCCESS_MESSAGE, removed, tasks.numTasks()));
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
