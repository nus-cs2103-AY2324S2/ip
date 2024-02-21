package meanduke.commands;

import meanduke.exceptions.MeanDukeException;

/**
 * This class represents a Command to end the current session and exit the program.
 */
public class ExitCommand extends Command {

    @Override
    public String execute() throws MeanDukeException {
        return "Finally you're finished, thought you would never stop yapping.";
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
