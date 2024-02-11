package podz.commands;

/**
 * Represents a command to handle incorrect commands in the task manager.
 */
public class IncorrectCommand extends Command {
    private Exception e;

    /**
     * Constructs an IncorrectCommand object with the specified exception.
     *
     * @param e the exception to be handled
     */
    public IncorrectCommand(Exception e) {
        this.e = e;
    }

    /**
     * Executes the incorrect command to print the error message.
     *
     * @param ui the user interface that interacts with the user
     */
    @Override
    public String execute() {
        super.response = this.e.getMessage();
        return super.response;
    }
}
