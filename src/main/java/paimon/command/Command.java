package paimon.command;

import paimon.storage.Storage;
import paimon.task.TaskList;
import paimon.ui.Ui;
import paimon.exception.ChatBotParameterException;

public abstract class Command {
    protected final String keyword;
    protected final String parameters;

    protected Command(String keyword, String parameters) {
        this.keyword = keyword;
        this.parameters = parameters;
    }

    /**
     * Checks if it is an Exit Command.
     * @return false by default.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command based on its keyword and parameters.
     * @param storage Storage for certain commands to access Local Storage.
     * @param ui Ui for certain commands to read from console and print to console.
     * @param taskList TaskList to save information of tasks.
     * @return String output to be sent to GUI for display.
     * @throws ChatBotParameterException for any error while handling Command parameters.
     */
    abstract public String execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException;
}
