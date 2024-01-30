package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;

/**
 * ByeCommand class represents a command for exiting the application.
 * It extends the Command class and implements the execute method to perform the exit logic.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendGoodbyeMessage();
        System.exit(0);
    }
}
