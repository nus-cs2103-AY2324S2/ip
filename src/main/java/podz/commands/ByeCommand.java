package podz.commands;

/**
 * Represents a command to say goodbye in the task manager.
 */
public class ByeCommand extends Command {
    /**
     * Executes the command to say goodbye to the user.
     */
    @Override
    public String execute() {
        super.response = "Goodbye for now!\n"
                + "It was a pleasure assisting you, Podz is here whenever you need help.";
        return super.response;
    }
}
