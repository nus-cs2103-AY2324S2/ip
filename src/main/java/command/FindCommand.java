package command;

import exceptions.DukeException;
import task.TaskList;
import utilities.Storage;

/**
 * Controls what to do when user wants to find keyword.
 */
public class FindCommand extends Command {
    /**
     * The keyword that the user is filtering.
     */
    private String keyword;

    /**
     * FindCommand class constructor.
     * @param keyword The keyword that the user is filtering.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the find task process.
     * @param taskList The task list that the task is filtered from.
     * @param storage The storage that the task list is stored in.
     * @return The response expected from the chatbot.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        TaskList foundTasks = taskList.find(this.keyword);
        return taskList.findToString(this.keyword);
    }
}
