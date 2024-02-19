package zhen.command;
import zhen.*;
/**
 * Represents the command of exiting the application
 */
public class ExitCommand extends Command {
    /**
     * Executes the command of exiting the program, display bye message to user.
     * @param taskList Task list potentially be worked on, not used in this program.
     * @param ui UI to manage the interaction with users.
     * @param storage Store the product of execution locally, not used in this program.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String replyMessage = "Bye";
        Ui.print_message(replyMessage);
        return replyMessage;
    }
    /**
     * Determines whether this command is an exit command.
     *
     * @return Ture, because this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
