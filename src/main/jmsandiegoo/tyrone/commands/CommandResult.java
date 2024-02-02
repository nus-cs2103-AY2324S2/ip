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

    @Override
    public String toString() {
        return this.commandResult;
    }
}
