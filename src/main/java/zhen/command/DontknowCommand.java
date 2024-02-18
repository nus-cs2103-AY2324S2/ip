package zhen.command;
import zhen.*;

/**
 * Represent command of showing user that this program don't understand s/he command.
 */
public class DontknowCommand extends Command {
    /**
     * Display message to user to tell that the program don't know what user's input means
     * @param taskList the task list potentially be worked on, not used in this program
     * @param ui to manage the interaction with users
     * @param storage to store the product of execution locally, not used in this program
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String replyMessage = "OOPS!!! I'm sorry, but I don't know what that means";
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
