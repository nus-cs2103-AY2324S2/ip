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
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) {
        return ui.exitMessage();
    }
}
