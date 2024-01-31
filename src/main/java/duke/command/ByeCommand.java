package duke.command;

import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

/**
 * Exits the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs ByeCommand.
     */
    public ByeCommand() {
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u) {
        u.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}