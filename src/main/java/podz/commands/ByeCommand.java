package podz.commands;

/**
 * Represents a command to say goodbye in the task manager.
 */
public class ByeCommand extends Command {
    /**
     * Executes the command to say goodbye to the user.
     *
     * @param ui the user interface for interaction
     */
    @Override
    public String execute() {
        super.response = "Bye. Hope to see you again soon!";
        return super.response;
    }
}
