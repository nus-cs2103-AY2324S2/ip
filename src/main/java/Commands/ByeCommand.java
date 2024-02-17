package Commands;

public class ByeCommand extends Command {

    static final String COMMAND_FORMAT = "bye";

    public ByeCommand() {
        super(COMMAND_FORMAT, "bye");
    }

    public String execute() {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.BYE;
    }
}
