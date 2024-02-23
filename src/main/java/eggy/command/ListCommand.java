package eggy.command;

import eggy.response.Response;
import eggy.storage.Storage;
import eggy.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Prints all tasks in the task list.
     *
     * @param tasks The task list of the chatbot.
     * @param response The response of the chatbot.
     * @param storage The storage of the chatbot.
     */
    @Override
    public void execute(TaskList tasks, Response response, Storage storage) {
        if (tasks.isEmpty()) {
            response.setTaskListEmptyResponse();
        } else {
            response.setTaskListResponse(tasks);
        }
    }
}
