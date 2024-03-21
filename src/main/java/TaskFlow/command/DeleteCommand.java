package TaskFlow.command;

import TaskFlow.exception.TaskFlowException;
import TaskFlow.storage.Storage;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

/**
 * A class that inherits from Command class.
 * Represents a command to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by removing the task at the specified index from the
     * TaskList, displaying a deletion message, and saving the updated TaskList to storage.
     * Saves the changes into the file.
     *
     * @param tasks         The list of tasks.
     * @param archiveTasks  The list of archive tasks.
     * @param ui            The Ui to interact with the user.
     * @param storage       The Storage to save the tasks to a file.
     * @param archived      The storage to save the archived tasks to a file.
     * @throws TaskFlowException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, TaskList archiveTasks, Ui ui,
                          Storage storage, Storage archived) throws TaskFlowException {
        if (this.index <= tasks.getTaskSize() && this.index > 0) {
            String s = ui.showDeleteMsg(tasks.getTasks().get(this.index - 1),
                    tasks.getTaskSize());
            tasks.delete(this.index);
            storage.saveTask(tasks);
            return s;
        } else {
            throw new TaskFlowException("Invalid index. \n"
                    + "Please provide a valid index within the range 1 to "
                    + tasks.getTaskSize() + "." + "\n");
        }
    }
}
