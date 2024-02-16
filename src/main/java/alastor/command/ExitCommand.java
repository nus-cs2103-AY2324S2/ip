package alastor.command;

import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
