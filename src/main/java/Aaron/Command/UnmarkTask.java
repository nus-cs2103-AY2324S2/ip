package aaron.command;

import aaron.exception.AaronBotException;
import aaron.exception.IndexFormatException;
import aaron.parser.IndexParser;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Class that represents a unmark task command
 */
public class UnmarkTask extends Command {
    public UnmarkTask(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
    }

    @Override
    public void run(TaskList taskList, UI ui) {
        int index;
        try {
            index = IndexParser.getIndex(commandDetails);
        } catch (IndexFormatException e) {
            ui.errorMessage(e);
            return;
        }
        try {
            taskList.unmark(index);
        } catch (AaronBotException e) {
            ui.errorMessage(e);
            return;
        }
        ui.unmarkMessage(index, taskList);
    }

    @Override
    public boolean returnIsBye() {
        return false;
    }

}
