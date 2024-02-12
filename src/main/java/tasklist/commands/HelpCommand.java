package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

/** Represents a command to display help information. Implements the Command interface. */
public class HelpCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelpMessage();
    }
}
