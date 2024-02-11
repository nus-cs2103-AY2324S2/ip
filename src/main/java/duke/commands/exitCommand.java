package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class exitCommand extends Command{

    /**
     * Constructs a new ExitCommand object.
     */
    public exitCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        ui.showGoodbye();
        return true;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
