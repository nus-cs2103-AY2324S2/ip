package bytebuddy.commands;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to create and add a Deadline task to the task list based on the provided information.
 */
public class DeadlineCommand implements Command {
    private String info;

    /**
     * Constructs a DeadlineCommand with the provided information.
     *
     * @param info The information containing details about the Deadline task.
     */
    public DeadlineCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the DeadlineCommand, adding a Deadline task to the task list.
     *
     * @param taskList The task list to which the Deadline task will be added.
     * @param ui       The user interface.
     * @param storage  The storage for saving and loading data.
     * @throws ByteBuddyException If there is an error executing the DeadlineCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException {
        return taskList.deadline(info);
    }

    /**
     * Checks if the DeadlineCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as DeadlineCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
