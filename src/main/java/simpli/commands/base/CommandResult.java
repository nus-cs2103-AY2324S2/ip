package simpli.commands.base;

/**
 * The outcome of executing a command.
 */
public class CommandResult {
    /** The specific CommandWord. */
    private final CommandWord status;

    /** The response returned from the command. */
    private final String commandOutput;

    /**
     * Constructs a CommandResult with the specified fields.
     *
     * @param status which is the specific command.
     * @param commandOutput which is the response String to be shown to user.
     */
    public CommandResult(CommandWord status, String commandOutput) {
        this.status = status;
        this.commandOutput = commandOutput;
    }

    /**
     * Returns the specific CommandWord.
     *
     * @return CommandWord.
     */
    public CommandWord getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return commandOutput;
    }
}
