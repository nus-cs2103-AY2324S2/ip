package TaskFlow.command;

import TaskFlow.exception.TaskFlowException;
import TaskFlow.storage.Storage;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

/**
 * A class that inherits from Command class.
 * Represents a command to display the list of tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by displaying the list of tasks to the user.
     *
     * @param tasks         The list of tasks.
     * @param archiveTasks  The list of archived tasks.
     * @param ui            The Ui to interact with the user.
     * @param storage       The Storage to save the tasks to a file.
     * @param archived      The storage to save the archived tasks to a file.
     * @throws TaskFlowException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, TaskList archiveTasks, Ui ui,
                          Storage storage, Storage archived) throws TaskFlowException {
        return ui.showList() + tasks.list();
    }
}
