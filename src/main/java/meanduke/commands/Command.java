package meanduke.commands;

import meanduke.exceptions.MeanDukeException;

/**
 * This class represents a Command that is given by the user that carries a specific action.
 */
public abstract class Command {

    /**
     * Returns the string describing the proper format for this Command.
     */
    public static String getUsage() {
        return "Usage:";
    }

    /**
     * Carries out the actions of this command
     *
     * @throws MeanDukeException if execution issues occur.
     */
    public abstract String execute() throws MeanDukeException;

    /**
     * Returns if this Command is an ExitCommand.
     */
    public boolean isExitCommand() {
        return false;
    }

}
