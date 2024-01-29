package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private static final String SUCCESS_MESSAGE = "Nice! Uncle marked this task as done:\n\t\t %s";
    private final String message;

    public MarkCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int index = Integer.parseInt(message);
            tasks.get(index - 1).mark();
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
