package aaron.command;

import aaron.exception.AaronBotException;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Class that represents a show list command
 */
public class ShowList extends Command {
    public ShowList(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
        assert commandType == CommandType.SHOW_LIST: "Command type should be show list";
    }

    @Override
    public String run(TaskList taskList, UI ui) {
        if (taskList.getTasklistSize() > 0) {
            return taskList.showList();
        } else {
            return ui.emptyListMessage();
        }
    }

    @Override
    public boolean returnIsBye() {
        return false;
    }

}
