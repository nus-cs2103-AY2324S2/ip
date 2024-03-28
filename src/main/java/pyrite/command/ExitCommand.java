package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * {inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return null;
    }
}
