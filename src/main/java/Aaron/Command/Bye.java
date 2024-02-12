package Aaron.Command;
import Aaron.Exception.AaronBotException;
import Aaron.Task.TaskList;
import Aaron.UI.UI;

/**
 * Class that represents a bye command
 */
public class Bye extends Command {
    public Bye(String userInput, CommandType commandType) throws AaronBotException{
        super(userInput, commandType);
    }

    @Override
    public void run(TaskList taskList, UI ui) {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isBye() {
        return true;
    }

    
}
