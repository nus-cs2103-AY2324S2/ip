public class CommandResult {
    private final String commandResult;

    public CommandResult(String commandResult) {
        this.commandResult = commandResult;
    }

    @Override
    public String toString() {
        return this.commandResult;
    }
}
