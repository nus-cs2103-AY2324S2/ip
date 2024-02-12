package Aaron.Command;
import Aaron.Exception.AaronBotException;
import Aaron.Exception.IndexFormatException;
import Aaron.Parser.IndexParser;
import Aaron.Task.TaskList;
import Aaron.UI.UI;

/**
 * Class that represents a mark task command
 */
public class MarkTask extends Command {
    public MarkTask(String userInput, CommandType commandType) throws AaronBotException{
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
            taskList.mark(index);
        } catch (AaronBotException e) {
            ui.errorMessage(e);
            return;
        }
        ui.markMessage(index, taskList);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
