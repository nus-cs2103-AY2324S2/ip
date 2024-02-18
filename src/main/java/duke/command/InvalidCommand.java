package duke.command;

public class InvalidCommand extends Command {
    public String output;

    public InvalidCommand(String output) {
        this.output = output;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(output);
    }
}
