package zhen.command;
import zhen.*;
/**
 * Represent the command of exiting the application
 */
public class ExitCommand extends Command {
    /**
     * Execute the command of exiting the program, display bye message to user
     * @param taskList the task list potentially be worked on, not used in this program
     * @param ui to manage the interaction with users
     * @param storage to store the product of execution locally, not used in this program
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String replyMessage = "Bye";
        Ui.print_message(replyMessage);
        return replyMessage;
    }
    /**
     * Determine whether this command is an exit command
     *
     * @return ture, because this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
