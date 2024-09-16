package fredricksen.commands;

/**
 * Represents a "Bye" Command, which extends the Command class.
 * A "Bye" Command is a command that overrides the execute method
 * to perform an exit program operation when called.
 */
public class ByeCommand extends Command {
    public ByeCommand() {};

    @Override
    public String execute() {
        return "Bye! Sad to see you leave! Please come again!";
    }
}
