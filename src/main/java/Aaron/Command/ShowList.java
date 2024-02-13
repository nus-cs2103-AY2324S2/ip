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
    }

    @Override
    public void run(TaskList taskList, UI ui) {
        if (taskList.getTasklistSize() > 0) {
            taskList.showList();
        } else {
            ui.emptyListMessage();
        }
    }

    @Override
    public boolean returnIsBye() {
        return false;
    }

}
