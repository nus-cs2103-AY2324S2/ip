package dude.Commands;

/**
 * The InvalidCommand class represents a command that is invalid.
 */
public class InvalidCommand extends Command {

    /**
     * Constructor for the InvalidCommand class. Returns a command object that is used to represent an invalid command.
     */
    public InvalidCommand() {
        super("", "");
    }

    /**
     * Does nothing
     *
     * @return The string message for an invalid command.
     */
    public String execute() {
        return "Sorry I don't understand that command. Please try again.";
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.INVALID;
    }
}
