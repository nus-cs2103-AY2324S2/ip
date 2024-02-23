package TaskFlow.command;

import TaskFlow.exception.TaskFlowException;
import TaskFlow.storage.Storage;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

/**
 * A class that inherits from the Command class.
 * Represents a command to find tasks containing a specified keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks with the specified keyword.
     *
     * @param tasks         The list of tasks to search.
     * @param archiveTasks  The list of archive tasks.
     * @param ui            The user interface for displaying messages.
     * @param storage       The storage for saving tasks to a file.
     * @param archived      The storage to save the archived tasks to a file.
     * @throws TaskFlowException If the keyword is not found in the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, TaskList archiveTasks, Ui ui,
                          Storage storage, Storage archived) throws TaskFlowException {
        if (!tasks.findTasksByKeyword(keyword).isEmpty()) {
            return ui.showFindMsg(tasks.findTasksByKeyword(keyword));
        } else {
            throw new TaskFlowException("Keyword is not found.");
        }
    }
}
