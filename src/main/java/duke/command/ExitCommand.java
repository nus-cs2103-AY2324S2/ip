package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * Class representing a Command that exits out of the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Runs the ExistCommand to exit out of the Chatbot.
     *
     * @param taskList Existing TaskList to be updated.
     * @param ui Existing UserInterface Object.
     * @param storage Existing Storage to be updated
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showExit();
    }

    /**
     * Returns a boolean value telling us whether this command is an exit command.
     *
     * @return Boolean value indicating whether this command is an exist command.
     */
    public boolean isExit() {
        return true;
    }
}
