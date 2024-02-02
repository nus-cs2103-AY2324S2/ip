package dave.exceptions;

import dave.TaskList;

public class UnableToFindTaskException extends ChatbotException {

    /**
     * Creates new UnableToFindTaskException.
     * UnableToFindTaskExceptions are those that occur when the user wants to delete/mark/unmark an invalid task.
     * 
     * @param taskList Current task list.
     */
    public UnableToFindTaskException(TaskList taskList) {
        super("Dave could not find the specified task." +
        "\nNote that there are " + taskList.getNumberOfTasks() + " task(s) currently.");
    }
}
