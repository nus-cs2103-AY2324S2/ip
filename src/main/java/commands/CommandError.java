package commands;


public class CommandError implements Command {
    private final String message;

    public CommandError(String message) {
        this.message = message;
    }
    @Override
    public String execute() {
        return this.message;
    }
}
