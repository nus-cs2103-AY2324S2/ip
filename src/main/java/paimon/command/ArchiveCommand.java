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
