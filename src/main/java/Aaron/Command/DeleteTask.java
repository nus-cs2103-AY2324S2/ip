package Aaron.Command;
import Aaron.Exception.AaronBotException;
import Aaron.Exception.IndexFormatException;
import Aaron.Exception.TaskListOutOfBoundsException;
import Aaron.Parser.IndexParser;
import Aaron.Task.TaskList;
import Aaron.UI.UI;

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
    public boolean isBye() {
        return false;
    }
}
