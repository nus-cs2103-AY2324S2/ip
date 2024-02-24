package kwuntalk.command;

import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;


/**
 * Represents the command to exit the chatbot.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command and generates the response.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @return The reply to the user's input.
     */
    @Override
    public String generateReply(TaskList taskList, Ui ui, Storage storage) {
        ui.close();
        return ui.showBye();
    }


    /**
     * Returns true if it is a bye command.
     *
     * @return True for Bye commands.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
