package eggy.command;

import java.util.List;

import eggy.exception.IncompleteFindCommandException;
import eggy.response.Response;
import eggy.storage.Storage;
import eggy.task.Task;
import eggy.task.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    /** The keyword to search for. */
    private String keyword;

    /**
     * Constructs a FindCommand.
     *
     * @param commands The array of commands.
     * @throws IncompleteFindCommandException If the task description is incomplete.
     */
    public FindCommand(String... commands) throws IncompleteFindCommandException {
        if (commands.length < 2) {
            throw new IncompleteFindCommandException();
        }
        this.keyword = commands[1];
    }

    /**
     * Finds tasks in the task list that match the keyword and prints them.
     *
     * @param tasks The task list of the chatbot.
     * @param response The response of the chatbot.
     * @param storage The storage of the chatbot.
     */
    @Override
    public void execute(TaskList tasks, Response response, Storage storage) {
        List<Task> matchingTasks = tasks.findMatchingTasks(this.keyword);
        response.setMatchingTasksResponse(matchingTasks);
    }
}
