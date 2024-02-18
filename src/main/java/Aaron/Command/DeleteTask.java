package aaron.command;

import aaron.exception.AaronBotException;
import aaron.exception.IndexFormatException;
import aaron.exception.TaskListOutOfBoundsException;
import aaron.parser.IndexParser;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Class that represents a delete task command
 */
public class DeleteTask extends Command {
    public DeleteTask(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
    }

    @Override
    public String run(TaskList taskList, UI ui) {
        int index;
        try {
            index = IndexParser.getIndex(commandDetails);
        } catch (IndexFormatException e) {
            return ui.errorMessage(e);
        }
        try {
            taskList.deleteTask(index);
        } catch (TaskListOutOfBoundsException e) {
            return ui.errorMessage(e);
        }
        return ui.showTaskDeletedMsg(index);
    }

    @Override
    public boolean returnIsBye() {
        return false;
    }
}
