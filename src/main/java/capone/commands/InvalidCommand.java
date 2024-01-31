package capone.commands;

import capone.TaskList;
import capone.TaskStorage;
import capone.Ui;
import capone.exceptions.InvalidCommandException;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage storage) throws InvalidCommandException {
        throw new InvalidCommandException("I'm sorry, I don't understand what you just said.\n" +
                "Use 'help' to display the list of valid commands");
    }
}
