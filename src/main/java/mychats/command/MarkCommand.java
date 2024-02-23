package mychats.command;
import mychats.exception.MyChatsException;
import mychats.main.Storage;
import mychats.main.TaskList;
import mychats.main.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {

    private int indexToMark;

    /**
     * Constructs a MarkCommand to mark the task with the
     * given task number as done.
     *
     * @param oneItem The one-indexed task number to be marked as done.
     */
    public MarkCommand(int oneItem) {
        this.indexToMark = oneItem - 1;
    }

    /**
     * Executes the MarkCommand by marking the task with
     * the given task number as done in the task list,
     * displaying the completion message, and saving
     * the updated task list using Storage.
     *
     * @param tasks askList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws MyChatsException If asked to delete a task with task number 0
     * or task number greater than the size of the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MyChatsException {
        int oneIndex = indexToMark + 1;
        boolean isOutsideLowerLimit = oneIndex < 1;
        boolean isOutsideUpperLimit = oneIndex > tasks.getSize();
        if (isOutsideLowerLimit || isOutsideUpperLimit) {
            throw new MyChatsException("Error! Task number '" + oneIndex + "' does not exist.");
        }
        tasks.markTask(indexToMark);
        ui.printToScreen("Nice! I've marked this task as done:\n" +
                tasks.get(indexToMark));
        storage.saveList(tasks.getTasks());
    }
}
