package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the Command that can be executed by the user.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The TaskList that is used by the user.
     * @param ui The Ui that is used by the user to interact with the chatbot.
     * @param storage The Storage that is used to save the chat history.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns true if the command is an Exit command.
     */
    public abstract boolean isExit();
}
