package duke.commands;

import duke.core.Ui;
import duke.exceptions.MeanDukeException;

/**
 * This class represents a Command to end the current session and exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute() throws MeanDukeException {
        Ui.printOutro();
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
