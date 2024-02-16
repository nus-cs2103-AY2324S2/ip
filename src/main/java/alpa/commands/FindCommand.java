package alpa.commands;

import java.util.List;

import alpa.exceptions.AlpaException;
import alpa.tasks.Task;
import alpa.tasks.TaskList;
import alpa.ui.Ui;
import alpa.utils.Storage;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand, finding tasks that match the keyword and displaying them.
     *
     * @param taskList The task list to search for tasks.
     * @param ui The user interface to display the found tasks.
     * @param storage The storage to save the task list.
     * @throws AlpaException If there is an error executing the command.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
        List<Task> foundTasks = taskList.findTasksByKeyword(keyword);
        ui.showFoundTasks(foundTasks);
    }

    /**
     * Checks if the FindCommand is an exit command.
     *
     * @return False, as the FindCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
