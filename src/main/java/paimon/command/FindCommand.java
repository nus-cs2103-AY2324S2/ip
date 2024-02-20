package paimon.command;

import paimon.exception.ChatBotParameterException;
import paimon.storage.Storage;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    public FindCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException {
        List<Task> taskListSearchResult = taskList.findFromKeyword(parameters);
        return ui.showFindResult(taskListSearchResult);
    }
}
