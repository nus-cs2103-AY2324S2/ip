package podz.commands;

import podz.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.printBye();
    }
    
}
