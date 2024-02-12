package duke.command;

import duke.exception.ChatBotParameterException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
