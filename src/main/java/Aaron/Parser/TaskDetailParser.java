package aaron.parser;

import aaron.exception.InvalidTaskTypeException;
import aaron.task.TaskType;
/**
 * Class that encapsulates means of obtaining task type and details from user input for an add task command
 */
public class TaskDetailParser {
    /**
     * Method to return task type based on user input
     * @param userInput user input
     * @return type of task to be added
     * @throws InvalidTaskTypeException if unknown task type given
     */
    public static TaskType getTaskType(String userInput) throws InvalidTaskTypeException{
        String[] tokenizedCommandDetails = userInput.split(" ", 2);
        return TaskType.getTaskType(tokenizedCommandDetails[0]);
    }

    /**
     * Method to obtain task description
     * @param userInput user input
     * @return task details 
     */
    public static String getTaskDetails(String userInput) {
        String[] tokenizedCommandDetails = userInput.split(" ", 2);
        if (tokenizedCommandDetails.length < 2) {
            return null;
        } else {
            return tokenizedCommandDetails[1];
        }
    }
}
