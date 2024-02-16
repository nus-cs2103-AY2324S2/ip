package bytebuddy.commands;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to mark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private String info;

    /**
     * Constructs an UnmarkCommand with the specified information.
     *
     * @param info The information associated with the UnmarkCommand.
     */
    public UnmarkCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the UnmarkCommand, marking a task as not done in the task list.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface for displaying information.
     * @param storage  The storage for saving and loading data.
     * @throws ByteBuddyException If there is an error executing the UnmarkCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException {
        return taskList.unmark(info);
    }

    /**
     * Checks if the UnmarkCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as UnmarkCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
