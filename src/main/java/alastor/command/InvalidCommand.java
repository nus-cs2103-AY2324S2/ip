package alastor.command;

import alastor.AlastorException;
import alastor.Storage;
import alastor.TaskList;
import alastor.Ui;

/**
 * Represents a command that is not recognised by the program.
 */
public class InvalidCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlastorException {
        throw new AlastorException("I'm sorry, but I don't know what that means :-(");
    }
}
