package liv.processor;

import java.util.ArrayList;

import liv.container.Storage;
import liv.container.TaskList;
import liv.exception.LivException;
import liv.ui.Ui;

/**
 * Represents a command for finding tasks that match a specified keyword.
 * <p>
 * This command searches through the task list for tasks containing the keyword and returns a message listing the matching tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Executes the find command by searching for tasks that contain the keyword.
     * Returns a message containing the list of matching tasks.
     *
     * @param tasks    The task list to search through.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for task data.
     * @return A message with the list of tasks matching the keyword.
     * @throws LivException if an error occurs during the execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LivException {
        ArrayList<String> matchingTasks = tasks.findMatchingTasks(keyword);
        String message = Ui.getFindMessage(matchingTasks);
        return message;
    }
}
