package capone.commands;

import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.InvalidCommandException;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException("I'm sorry, I don't understand what you just said.\n"
                + "Use 'help' to display the list of valid commands");
    }
}
