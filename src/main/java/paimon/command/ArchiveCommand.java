package paimon.command;

import paimon.exception.ChatBotParameterException;
import paimon.storage.Storage;
import paimon.task.TaskList;
import paimon.ui.Ui;

import java.io.IOException;

public class ArchiveCommand extends Command{

    public ArchiveCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    /**
     * Execute the command based on its keyword and parameters
     *
     * @param storage  Storage for certain commands to access Local Storage
     * @param ui       Ui for certain commands to read from console and print to console
     * @param taskList TaskList to save information of tasks
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException {
        try {
            String fileName = storage.saveTaskListToFile(parameters, taskList);
            return ui.showArchiveSuccess(fileName);
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
    }
}
