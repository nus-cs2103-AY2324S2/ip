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

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException {
        try {
            return ui.showArchiveList(storage.loadArchiveList());
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
    }
}
