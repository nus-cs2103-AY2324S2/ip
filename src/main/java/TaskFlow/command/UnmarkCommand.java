package TaskFlow.command;

import TaskFlow.exception.TaskFlowException;
import TaskFlow.storage.Storage;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

/**
 * A class that inherits from Command class.
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index of the task to unmark.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Unmark Command by setting the specified task as not done in
     * the TaskList.
     * Shows a message indicating the task has been unmarked.
     * Saves the changes into the file.
     *
     * @param tasks         The TaskList that holds the list of tasks.
     * @param archiveTasks  The list of archived tasks.
     * @param ui            The Ui to interact with the user.
     * @param storage       The Storage to save the tasks to a file.
     * @param archived      The storage to save the archived tasks to a file.
     * @throws TaskFlowException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, TaskList archiveTasks, Ui ui,
                          Storage storage, Storage archived) throws TaskFlowException {
        if (this.index <= tasks.getTaskSize() && this.index > 0) {
            tasks.unmark(this.index);
            storage.saveTask(tasks);
            return ui.showUnmarkMsg(tasks.getTasks().get(this.index - 1));
        } else {
            throw new TaskFlowException("Invalid index. "
                    + "Please provide a valid index within the range 1 to "
                    + tasks.getTaskSize() + ".");
        }
    }
}
