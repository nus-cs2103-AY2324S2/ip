package bytebuddy.commands;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to delete a task from the task list based on the provided task number.
 */
public class DeleteCommand implements Command {
    private String info;

    /**
     * Constructs a DeleteCommand with the provided information.
     *
     * @param info The information containing the task number to be deleted.
     */
    public DeleteCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the DeleteCommand, deleting a task from the task list.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui       The user interface.
     * @param storage  The storage for saving and loading data.
     * @throws ByteBuddyException If there is an error executing the DeleteCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException {
        return taskList.delete(info);
    }

    /**
     * Checks if the DeleteCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as DeleteCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
