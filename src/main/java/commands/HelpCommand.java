package commands;

import ui.Ui;

/**
 * Encapsulates a help command.
 */
public class HelpCommand extends Command {

    public static final String COMMAND = "help";

    /**
     * Prints out the help tool.
     *
     * @return A list of valid commands.
     */
    @Override
    public String executeCommand() {
        return Ui.helpMessage();
    }
}
