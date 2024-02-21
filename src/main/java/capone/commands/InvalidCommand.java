package capone.commands;

import capone.Storage;
import capone.TaskList;
import capone.exceptions.InvalidCommandException;
import capone.ui.Ui;

/**
 * Represents a command to handle invalid user commands.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class InvalidCommand extends Command {
    /**
     * Executes the InvalidCommand, throwing an InvalidCommandException with a helpful message.
     *
     * @param taskList The TaskList (not used in this command).
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data (not used in this command).
     * @return The String output of the bot after executing the user's command (not used).
     * @throws InvalidCommandException If an invalid command is encountered.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException("I'm sorry, I don't understand what you just said.\n"
                + "Use 'help' to display the list of valid commands");
    }
}
