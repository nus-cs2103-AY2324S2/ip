package jmsandiegoo.tyrone.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String commandResult;

    /**
     * @param commandResultStr string that is to be displayed to the user.
     */
    public CommandResult(String commandResultStr) {
        this.commandResult = commandResultStr;
    }

    public CommandResult chain(CommandResult... otherCommandResults) {
        CommandResult newCommandResult = new CommandResult(this.commandResult);
        for (CommandResult otherCommandResult : otherCommandResults) {
            newCommandResult = new CommandResult(newCommandResult.commandResult
                    + otherCommandResult.commandResult);
        }
        return newCommandResult;
    }

    @Override
    public String toString() {
        return this.commandResult;
    }
}
