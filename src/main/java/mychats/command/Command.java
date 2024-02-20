package mychats.command;
import mychats.exception.MyChatsException;
import mychats.main.Storage;
import mychats.main.TaskList;
import mychats.main.Ui;

/**
 * Represents a command that can be executed.
 */
public class Command {

    /**
     * Executes the command given the TaskList, Ui, and Storage.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws MyChatsException If there is an error executing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MyChatsException {

    }

    /**
     * Checks if command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
