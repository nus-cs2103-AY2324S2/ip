package aaron.command;

import aaron.exception.AaronBotException;
import aaron.exception.IndexFormatException;
import aaron.parser.IndexParser;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Class that represents a mark task command
 */
public class MarkTask extends Command {
    public MarkTask(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
        assert commandType == CommandType.MARK: "Command type should be MARK";
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
            taskList.mark(index);
        } catch (AaronBotException e) {
            return ui.errorMessage(e);
        }
        return ui.markMessage(index, taskList);
    }
}
