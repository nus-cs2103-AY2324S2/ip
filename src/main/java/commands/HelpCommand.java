package commands;

import ui.Ui;

/**
 * Encapsulates a help command.
 */
public class HelpCommand extends Command {

    /**
     * Prints out the help tool.
     */
    @Override
    public void execute() {
        Ui.helpMessage();
    }
}
