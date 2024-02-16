package bytebuddy.commands;

import static bytebuddy.constants.Messages.BYE_MESSAGE;

import bytebuddy.exceptions.ByteBuddyException;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ByteBuddyException {
        // No action needed for ByeCommand
        return "\t " + BYE_MESSAGE;
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
