package aaron.executer;

import java.util.Map;

import aaron.command.AddTask;
import aaron.command.Command;
import aaron.command.CommandType;
import aaron.command.DeleteTask;
import aaron.command.MarkTask;
import aaron.command.SearchCommand;
import aaron.command.ShowList;
import aaron.command.UnmarkTask;
import aaron.exception.AaronBotException;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Class that represents an executer that executes commands based on parsed
 * command
 */
public class Executer {

    /**
     * Method to build command
     * 
     * @param commandType command type
     * @param userCommand user command details
     * @param taskList    tasklist to be modified
     * @param ui          UI to handle user interaction
     * @return command built
     * @throws AaronBotException if error run into during command building
     */
    public static Command buildCommand(CommandType commandType, String userCommand, TaskList taskList, UI ui)
            throws AaronBotException {
        try {
            Map<CommandType, Command> commandMap = Map.of(
                    CommandType.ADDTASK, new AddTask(userCommand, commandType),
                    CommandType.MARK, new MarkTask(userCommand, commandType),
                    CommandType.UNMARK, new UnmarkTask(userCommand, commandType),
                    CommandType.DELETE, new DeleteTask(userCommand, commandType),
                    CommandType.SEARCH, new SearchCommand(userCommand, commandType),
                    CommandType.SHOW_LIST, new ShowList(userCommand, commandType));

            Command command = commandMap.get(commandType);
            return command;
        } catch (AaronBotException e) {
            throw e;
        }
    }

}
