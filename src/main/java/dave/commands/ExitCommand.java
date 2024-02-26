package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     * Outputs exit message.
     * 
     * @return Message to show user when exiting.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.sayBye();
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
