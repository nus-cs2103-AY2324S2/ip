package Jerry.command;

import Jerry.Ui;

public class InvalidCommand extends Command{

    public InvalidCommand(Ui ui) {
        super(ui, null);
    }
    @Override
    public void execute() {
        ui.showMessage("Invalid Command");
    }
}
