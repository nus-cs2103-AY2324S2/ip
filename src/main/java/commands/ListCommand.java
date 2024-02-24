package commands;

import utils.Response;
import utils.TaskList;

/**
 * The ListCommand class represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param taskList The task list object.
     */
    @Override
    public void execute(TaskList taskList) {
        String response = taskList.getLength() == 0
                ? Response.getEmptyListResponse()
                : Response.getListTasksResponse(taskList);
        setResponse(response);
    }
}
