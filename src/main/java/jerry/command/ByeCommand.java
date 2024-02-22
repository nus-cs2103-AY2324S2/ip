package jerry.command;

import jerry.Ui;

public class ByeCommand extends Command {
    public ByeCommand(Ui ui) {
        super(ui, null); // No tasks needed for ByeCommand

    }

    @Override
    public String execute() {
        ui.closeScanner();
        return ui.showGoodbye();
    }
}
