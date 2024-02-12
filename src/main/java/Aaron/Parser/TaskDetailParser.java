package aaron.parser;

import aaron.exception.InvalidTaskTypeException;
import aaron.task.TaskType;

public class TaskDetailParser {

    public static TaskType getTaskType(String commandDetails) throws InvalidTaskTypeException {
        String[] tokenizedCommandDetails = commandDetails.split(" ", 2);
        return TaskType.getTaskType(tokenizedCommandDetails[0]);
    }

    public static String getTaskDetails(String commandDetails) {
        return commandDetails.split(" ", 2)[1];
    }
}
