package capone.commands;

import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.ui.Ui;

/**
 * Represents a command to display a list of supported commands.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class HelpCommand extends Command {
    /**
     * Executes the HelpCommand, displaying a list of supported commands.
     *
     * @param taskList The TaskList (not used in this command).
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data (not used in this command).
     * @return The String output of the bot after executing the user's command.
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        return ui.sendHelp();
    }
}
