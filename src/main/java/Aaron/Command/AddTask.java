package aaron.command;

import aaron.exception.AaronBotException;
import aaron.exception.InvalidTaskTypeException;
import aaron.exception.TaskErrorException;
import aaron.exception.TaskNoNameException;
import aaron.parser.TaskDetailParser;
import aaron.task.TaskList;
import aaron.task.TaskType;
import aaron.ui.UI;

/**
 * class that represents a command to add a task to the tasklist
 */
public class AddTask extends Command {
    public AddTask(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
        assert commandType == CommandType.ADDTASK: "Tasktype should be ADDTASK";
    }

    @Override
    public String run(TaskList taskList, UI ui) {
        TaskType taskType;
        String taskDetails;
        try {
            taskPresenceCheck(commandDetails);
        } catch (TaskNoNameException e) {
            return ui.errorMessage(e);
        }
        try {
            taskType = TaskDetailParser.getTaskType(commandDetails);
            taskDetails = TaskDetailParser.getTaskDetails(commandDetails);
        } catch (InvalidTaskTypeException e) {
            return ui.errorMessage(e);
        }
        try {
            taskList.addToList(taskType, taskDetails);
            return ui.taskAddedMessage(taskList);
        } catch (TaskErrorException e) {
            return ui.errorMessage(e);
        }
    }
    
    /**
     * Method to check if an add command is of the correct format
     * @param userInputString
     * @throws TaskNoNameException if the command is in incorrect format
     */
    private static void taskPresenceCheck(String userInputString) throws TaskNoNameException {
        if (userInputString.split("\\s+").length <= 1) {
            throw new TaskNoNameException(userInputString);
        }
    }
}
