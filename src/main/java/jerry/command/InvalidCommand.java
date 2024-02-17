package jerry.command;

import jerry.Ui;

public class InvalidCommand extends Command{

    public InvalidCommand(Ui ui) {
        super(ui, null);
    }
    @Override
    public String execute() {
        return ui.showMessage("Invalid Command");
    }
}
