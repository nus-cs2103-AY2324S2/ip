package aaron.command;

import aaron.exception.AaronBotException;
import aaron.parser.TaskDetailParser;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Abstract class that represents a user command
 */
public abstract class Command {
    public final String commandDetails;
    public final CommandType commandType;

    public Command(String userInput, CommandType commandType) throws AaronBotException {
        this.commandType = commandType;
        commandDetails = TaskDetailParser.getTaskDetails(userInput);
    }

    /**
     * Method that executes the command
     * 
     * @param taskList tasklist that will be modified/read from
     * @param ui       UI that handles user interaction
     */
    public abstract String run(TaskList taskList, UI ui);

    /**
     * Method that returns whether the command is a bye command
     * 
     * @return boolean val representing whether the command is a bye command
     */

    public abstract boolean returnIsBye();

}
