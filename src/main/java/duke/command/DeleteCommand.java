package duke.command;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeleteCommand extends Command {

    private int zeroItem;
    
    /**
     * Constructs a DeleteCommand given the task number to be deleted.
     *
     * @param oneItem One-indexed task number to be deleted.
     */
    public DeleteCommand(int oneItem) {
        this.zeroItem = oneItem - 1;
    }

    /**
     * Executes the DeleteCommand by deleting the given task
     * from the task list, displaying the deleted response using Ui,
     * and saving the updated task list using Storage.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws DukeException If asked to delete a task with task number 0
     * or task number greater than the size of the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = zeroItem + 1;
        boolean isOutsideLowerLimit = taskNumber < 1;
        boolean isOutsideUpperLimit = taskNumber > tasks.getSize();
        if (isOutsideLowerLimit || isOutsideUpperLimit || tasks.get(taskNumber - 1) == null) {
            throw new DukeException("Error! Task number '" + taskNumber + "' does not exist.");
        }
        Task description = tasks.get(zeroItem);
        tasks.deleteTask(zeroItem);
        ui.deleteResponse(description, tasks);
        storage.saveList(tasks.getTasks());
    }
}