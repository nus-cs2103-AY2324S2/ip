package dude.commands;

/**
 * The ByeCommand class represents a command to exit the program.
 */
public class ByeCommand extends Command {

    static final String COMMAND_FORMAT = "bye";

    /**
     * Constructor for the ByeCommand class. Returns a command object to exit the program upon execution.
     */
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
