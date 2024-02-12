package Duke.command;
import Duke.*;

/**
 * Represent command of listing all the task added by the user.
 */
public class ListCommand extends Command {
    /**
     * List all the task stored in the program.
     * @param taskList the task list storing the task the user operated on.
     * @param ui to manage the interaction with users
     * @param storage to store the product of execution locally, not used in this program
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.print_message(taskList.toString());
    }
    /**
     * Determine whether this command is an exit command
     *
     * @return false, because this is not an exit command
     */
    public boolean isExit() {
        return false;
    }
}
