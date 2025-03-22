package zhen.command;

import zhen.Storage;
import zhen.TaskList;
import zhen.Ui;

/**
 * Represents command of listing all the tasks added by the user.
 */
public class ListCommand extends Command {
    /**
     * Lists all the task stored in the program.
     * @param taskList Task list storing the task the user operated on.
     * @param ui UI to manage the interaction with users.
     * @param storage Store the product of execution locally, not used in this program.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String replyMessage = taskList.toString();
        Ui.print_message(replyMessage);
        return replyMessage;
    }
    /**
     * Determines whether this command is an exit command.
     *
     * @return False, because this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
