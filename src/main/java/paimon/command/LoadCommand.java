package paimon.command;

import paimon.exception.ChatBotParameterException;
import paimon.parser.Parser;
import paimon.storage.Storage;
import paimon.task.TaskList;
import paimon.ui.Ui;

public class LoadCommand extends Command{
    public LoadCommand(String keyword, String parameters) {
        super(keyword, parameters);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException {
        String fileName = Parser.parseArchiveFileLoad(parameters);
        TaskList archivedTaskList = new TaskList();
        storage.loadTasksFromFileToTaskList(archivedTaskList, fileName);
        taskList.updateTaskList(archivedTaskList);
        return ui.showLoadArchiveSuccess(fileName);
    }
}
