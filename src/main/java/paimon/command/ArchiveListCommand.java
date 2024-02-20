package paimon.command;

import paimon.exception.ChatBotParameterException;
import paimon.storage.Storage;
import paimon.task.TaskList;
import paimon.ui.Ui;

import java.io.IOException;

public class ArchiveListCommand extends Command{
    public ArchiveListCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * Executes the command based on its keyword and parameters
     *
     * @param storage  Storage for certain commands to access Local Storage
     * @param ui       Ui for certain commands to read from console and print to console
     * @param taskList TaskList to save information of tasks
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException {
        try {
            return ui.showArchiveList(storage.loadArchiveList());
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
    }
}
