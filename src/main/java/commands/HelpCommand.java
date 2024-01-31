package commands;

import commands.Command;
import ui.Ui;

/**
 * Encapsulates a help command
 */
public class HelpCommand extends Command {

    @Override
    public void execute() {
        Ui.helpMessage();
    }
}
