package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     * Outputs exit message.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }

    /**
     * {@inheritDoc}
     * Exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }    
}
