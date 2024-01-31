package commands;

import commands.Command;
import ui.Ui;

public class HelpCommand extends Command {

    @Override
    public void execute() {
        Ui.helpMessage();
    }
}
