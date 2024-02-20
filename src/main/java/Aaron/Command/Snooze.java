package aaron.command;

import aaron.exception.AaronBotException;
import aaron.exception.IndexFormatException;
import aaron.parser.IndexParser;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Class that encapsulates a snooze command
 */
public class Snooze extends Command {
    public Snooze(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
        assert commandType == CommandType.SNOOZE: "Command type should be snooze";
    }

    @Override
    public String run(TaskList taskList, UI ui) {
        int index;
        try {
            index = IndexParser.getIndex(commandDetails);
        } catch (IndexFormatException e) {
            return ui.errorMessage(e);
        }
        return taskList.postpone(index, 3, ui);
    }
}
