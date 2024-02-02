package bytebuddy.commands;

import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to add a todo task to the task list.
 */
public class TodoCommand implements Command {
    private String info;

    /**
     * Constructs a TodoCommand with the specified information.
     *
     * @param info The information associated with the TodoCommand.
     */
    public TodoCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the TodoCommand, adding a todo task to the task list.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface for displaying information.
     * @param storage  The storage for saving and loading data.
     * @throws ByteBuddyException If there is an error executing the TodoCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException {
        return taskList.todo(info);
    }

    /**
     * Checks if the TodoCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as TodoCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
