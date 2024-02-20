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
     * Check if it is an Exit Command
     * @return false by default
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute the command based on its keyword and parameters
     * @param storage Storage for certain commands to access Local Storage
     * @param ui Ui for certain commands to read from console and print to console
     * @param taskList TaskList to save information of tasks
     */
    abstract public String execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException;
}
