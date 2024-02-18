package aaron.command;

import aaron.exception.AaronBotException;
import aaron.task.TaskList;
import aaron.ui.UI;

public class SearchCommand extends Command {
    public SearchCommand(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
    }

    @Override
    public String run(TaskList taskList, UI ui) {
        return taskList.showKeywordTasklist(commandDetails, ui);
    }

    @Override
    public boolean returnIsBye() {
        return false;
    }
}
