package capone.commands;

import capone.TaskList;
import capone.Storage;
import capone.Ui;
import capone.exceptions.InvalidCommandException;

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
     * @throws InvalidCommandException If an invalid command is encountered.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException("I'm sorry, I don't understand what you just said.\n" +
                "Use 'help' to display the list of valid commands");
    }
}
