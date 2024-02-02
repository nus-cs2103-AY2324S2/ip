package dave.exceptions;

import dave.TaskList;

public class UnableToFindTaskException extends ChatbotException {
    public UnableToFindTaskException(TaskList taskList) {
        super("Dave could not find the specified task." + "\nNote that there are "
        + taskList.getNumberOfTasks() + " task(s) currently.");
    }
}
