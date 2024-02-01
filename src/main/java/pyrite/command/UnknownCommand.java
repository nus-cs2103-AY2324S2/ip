package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

/**
 * Command that represents an unknown command.
 */
public class UnknownCommand extends Command{
    private String command;
    private String reason;

    /**
     * Constructs an UnknownCommand.
     *
     * @param command The command string that was not recognised.
     * @param reason The reason the command was not recognised.
     */
    public UnknownCommand(String command, String reason) {
        this.command = command;
        this.reason = reason;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return reason + "\nYour command was: " + command;
    }
}
