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
    public String execute(Storage s, TaskList t, Ui u) {
        return u.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
