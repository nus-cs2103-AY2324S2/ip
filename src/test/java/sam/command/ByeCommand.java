package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;
import sam.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        System.out.println("Hope you enjoy my help.");
    }

    public boolean isExit() {
        return true;
    }
}
