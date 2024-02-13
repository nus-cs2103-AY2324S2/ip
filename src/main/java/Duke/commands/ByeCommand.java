package duke.commands;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * ByeCommand class to exit program when user types "bye"
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand
     */
    public ByeCommand() {
        super();
    }
    /**
     * Prints to user bye message
     * @param tasks TaskList containing all user inputted task
     * @param ui UI that interacts with user
     * @param s Storage Object that interacts with user file storage
     * @return true to allow program to exit
     */
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage s) {
        ui.displayExit();
        return true;
    }
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) {
        return ui.exitMessage();
    }
}
