package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private static final String SUCCESS_MESSAGE = "Nice! Uncle marked this task as done:\n\t\t %s";
    private final String message;

    public UnmarkCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int index = Integer.parseInt(message);
            tasks.get(index - 1).unmark();
            ui.showToUser(String.format(SUCCESS_MESSAGE, tasks.get(index - 1)));
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage(e.getMessage());
        }

        try {
            storage.appendToFile(tasks);
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }

    }
}
