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
    public void run(TaskList taskList, UI ui) {
        int index;
        try {
            index = IndexParser.getIndex(commandDetails);
        } catch (IndexFormatException e) {
            ui.errorMessage(e);
            return;
        }
        try {
            taskList.deleteTask(index);
        } catch (TaskListOutOfBoundsException e) {
            ui.errorMessage(e);
            return;
        }
        System.out.println("Successfully deleted task " + index);
    }

    @Override
    public boolean returnIsBye() {
        return false;
    }
}
