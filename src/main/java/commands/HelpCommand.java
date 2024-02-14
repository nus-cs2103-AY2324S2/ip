package commands;

import ui.Ui;

/**
 * Encapsulates a help command.
 */
public class HelpCommand extends Command {

    /**
     * Prints out the help tool.
     *
     * @return
     */
    @Override
    public String executeCommand() {
        return Ui.helpMessage();
    }
}
