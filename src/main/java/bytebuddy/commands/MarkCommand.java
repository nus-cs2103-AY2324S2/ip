package bytebuddy.commands;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to mark a task as done in the task list.
 */
public class MarkCommand implements Command {
    private String info;

    /**
     * Constructs a MarkCommand with the specified information.
     *
     * @param info The information associated with the MarkCommand.
     */
    public MarkCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the MarkCommand, marking a task as done in the task list.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface for displaying information.
     * @param storage  The storage for saving and loading data.
     * @throws ByteBuddyException If there is an error executing the MarkCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException {
        return taskList.mark(info);
    }

    /**
     * Checks if the MarkCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as MarkCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
