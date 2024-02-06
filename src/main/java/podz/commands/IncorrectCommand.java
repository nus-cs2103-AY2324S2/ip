package podz.commands;

import podz.ui.Ui;

public class IncorrectCommand extends Command {
    private Exception e;

    public IncorrectCommand (Exception e) {
        this.e = e;
    }

    @Override
    public void execute(Ui ui) {
        ui.printError(e);
    }
}
