package TaskFlow.command;

import TaskFlow.exception.TaskFlowException;
import TaskFlow.storage.Storage;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

/**
 * A class that inherits from the Command class.
 * Represents a command to exit the Duke program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by displaying a goodbye message to the user.
     *
     * @param tasks        The list of tasks.
     * @param archiveTasks The list of archive tasks.
     * @param ui           The Ui to interact with the user.
     * @param storage      The Storage to save the tasks to a file.
     * @param archived     The storage to save the archived tasks to a file.
     * @return The goodbye message.
     * @throws TaskFlowException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, TaskList archiveTasks, Ui ui,
                          Storage storage, Storage archived) throws TaskFlowException {
        return ui.showGoodbyeMsg();
    }
}
