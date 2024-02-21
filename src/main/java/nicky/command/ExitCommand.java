package nicky.command;

import nicky.Ui;
import nicky.task.Storage;
import nicky.task.TaskList;

/**
 * Represents a command to exit the Nicky application.
 * It allows the user to terminate the application gracefully.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
