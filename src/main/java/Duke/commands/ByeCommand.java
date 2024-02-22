package duke.commands;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Represents a command to exit the program when the user types "bye".
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes the ByeCommand to exit the program.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface object.
     * @param storage The storage object (not used in this command).
     * @return A goodbye message to display to the user.
     */
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) {
        // Get the text for a goodbye message from the UI object
        return ui.exitMessage();
    }
}

