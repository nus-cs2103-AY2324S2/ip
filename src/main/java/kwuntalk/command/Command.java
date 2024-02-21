package kwuntalk.command;

import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;
import kwuntalk.exception.KwunTalkException;


/**
 * Abstract representation of a command.
 */
public abstract class Command {

    /**
     * Executes the command and generates the response.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @return The reply to the user's input.
     * @throws KwunTalkException If command has error.
     */
    public abstract String generateReply(TaskList taskList, Ui ui, Storage storage) throws KwunTalkException;


    /**
     * Returns false if it is not a bye command.
     *
     * @return False for non-bye commands.
     */
    public boolean isExit() {
        return false;
    }
}
