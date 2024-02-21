package kwuntalk.command;

import kwuntalk.exception.KwunTalkException;
import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;


/**
 * Abstract representation of a command.
 */
public abstract class Command {

    /**
     * Abstract method of the execution of command and generation of the response.
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
