package aaron.executer;

import aaron.command.AddTask;
import aaron.command.Bye;
import aaron.command.CommandType;
import aaron.command.DeleteTask;
import aaron.command.MarkTask;
import aaron.command.SearchCommand;
import aaron.command.ShowList;
import aaron.command.UnmarkTask;
import aaron.exception.AaronBotException;
import aaron.exception.NonsenseCommandException;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Class that represents an executer that executes commands based on parsed command 
 */
public class Executer {

    /**
     * Method to execute command based on information parsed by parser
     * @param commandType type of command to execute
     * @param userCommand command description
     * @param taskList tasklist to be modified/read from (if neccessary)
     * @param ui UI to perform human interaction (if necessary)
     * @return boolean to indicate whether the command was a bye command (end execution cycle)
     */
    public static boolean execute(CommandType commandType, String userCommand, TaskList taskList, UI ui) {
        try {
            switch (commandType) {
            case ADDTASK:
                AddTask addCommand = new AddTask(userCommand, commandType);
                addCommand.run(taskList, ui);
                return addCommand.returnIsBye();
            case BYE:
                Bye byeCommand = new Bye(userCommand, commandType);
                byeCommand.run(taskList, ui);
                return byeCommand.returnIsBye();
            case MARK:
                MarkTask markCommand = new MarkTask(userCommand, commandType);
                markCommand.run(taskList, ui);
                return markCommand.returnIsBye();
            case UNMARK:
                UnmarkTask unmarkCommand = new UnmarkTask(userCommand, commandType);
                unmarkCommand.run(taskList, ui);
                return unmarkCommand.returnIsBye();
            case DELETE:
                DeleteTask deleteCommand = new DeleteTask(userCommand, commandType);
                deleteCommand.run(taskList, ui);
                return deleteCommand.returnIsBye();
            case SEARCH:
                SearchCommand searchCommand = new SearchCommand(userCommand, commandType);
                searchCommand.run(taskList, ui);
                return searchCommand.returnIsBye();
            case SHOW_LIST:
                ShowList showCommand = new ShowList(userCommand, commandType);
                showCommand.run(taskList, ui);
                return showCommand.returnIsBye();
            default:
                throw new NonsenseCommandException(
                        "Student I don't know this command: " + userCommand);
            }
        } catch (AaronBotException e) {
            System.out.println(e);
            return false;
        }
    }

}
