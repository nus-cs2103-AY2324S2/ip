package Aaron.Command;

import Aaron.Exception.AaronBotException;
import Aaron.Task.TaskList;
import Aaron.UI.UI;

public class SearchCommand extends Command {
    public SearchCommand(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
    }

    @Override
    public void run(TaskList taskList, UI ui) {
        taskList.showKeywordTasklist(commandDetails, ui);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
