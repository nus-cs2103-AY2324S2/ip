package commands;

import utils.TaskList;
import utils.Ui;

/**
 * Represents a command to find tasks in the task list that match a specific query.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Constructs a FindCommand with the specified query.
     *
     * @param query The query to search for within task descriptions.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the FindCommand, searching for tasks in the task list that match the specified query
     * and displays the found tasks using the user interface.
     *
     * @param ui        The user interface to display the found tasks.
     * @param taskList  The task list to search for tasks.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.listFoundTasks(taskList.findTasks(query));
    }
}
