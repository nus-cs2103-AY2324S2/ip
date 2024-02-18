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
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String replyMessage = taskList.toString();
        Ui.print_message(replyMessage);
        return replyMessage;
    }
    /**
     * Determine whether this command is an exit command
     *
     * @return false, because this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
