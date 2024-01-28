package bytebuddy.commands;

import bytebuddy.exceptions.DukeException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to exit the chatbot and say bye.
 */
public class ByeCommand implements Command {

    /**
     * Executes the ByeCommand, performing any necessary actions when the chatbot exits.
     * @param taskList   The task list on which the command operates.
     * @param ui      The user interface for displaying information to the user.
     * @param storage The storage for saving and loading data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        // No action needed for ByeCommand
    }

    /**
     * Checks if the ByeCommand is an exit command, indicating that the chatbot should exit.
     *
     * @return {@code true} as ByeCommand is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
