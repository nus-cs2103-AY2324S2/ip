package Commands;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super("", "");
    }

    public String execute() {
        return "Sorry I don't understand that command. Please try again.";
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.INVALID;
    }
}
