package Aaron.Command;
import Aaron.Exception.AaronBotException;
import Aaron.Parser.TaskDetailParser;
import Aaron.Task.TaskList;
import Aaron.UI.UI;

/**
 * Abstract class that represents a user command
 */
public abstract class Command {
    public final String commandDetails;
    public final CommandType commandType;

    public Command(String userInput, CommandType commandType) throws AaronBotException{
        this.commandType = commandType;
        commandDetails = TaskDetailParser.getTaskDetails(userInput);
    }

    /**
     * Method that executes the command
     * @param taskList tasklist that will be modified/read from
     * @param ui UI that handles user interaction
     */
    public abstract void run(TaskList taskList, UI ui);
    /**
     * Method that returns whether the command is a bye command
     * @return boolean val representing whether the command is a bye command
     */
    public abstract boolean isBye();


}
