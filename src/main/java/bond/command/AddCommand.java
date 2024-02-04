/**
 * The AddCommand class is used to classify an add command in the Bond task.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.command;

public abstract class AddCommand extends Command {

    public String taskName;

    /**
     * Constructor for the AddCommand class.
     * 
     * @param commandType The type of command.
     * @param taskName    The name of the task.
     */
    public AddCommand(String commandType, String taskName) {
        super(commandType);
        this.taskName = taskName;
    }
}
