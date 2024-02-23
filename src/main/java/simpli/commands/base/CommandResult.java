package simpli.commands.base;

public class CommandResult {
    private final CommandWord status;
    private final String commandOutput;

    public CommandResult(CommandWord status, String commandOutput) {
        this.status = status;
        this.commandOutput = commandOutput;
    }

    public CommandWord getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return commandOutput;
    }
}
