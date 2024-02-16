package podz.commands;

/**
 * Represents a command to list all tasks in the task manager.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks to the user.
     */
    @Override
    public String execute() {
        super.response = super.taskList.toString();
        return super.response;
    }
}
