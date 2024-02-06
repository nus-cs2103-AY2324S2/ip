package podz.commands;

import podz.ui.Ui;

/**
 * Represents a command to say goodbye in the task manager.
 */
public class ByeCommand extends Command {
    /**
     * Executes the command to say goodbye to the user.
     *
     * @param ui the user interface for interaction
     */
    @Override
    public void execute(Ui ui) {
        ui.printBye();
    }
}
