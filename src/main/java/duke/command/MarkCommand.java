package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IllegalParamException;
import duke.task.Task;
import duke.task.TaskList;
/**
 * Represents a command to mark a specified task.
 */
public class MarkCommand extends Command{
    private int index;
    /**
     * Constructs a mark command.
     *
     * @param index Task index to be unmarked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }
    /**
     * Executes the command, marking task at specified index as done.
     * Also displays messages to user.
     *
     * @param list TaskList object containing current tasks.
     * @param ui To send instructions on how to update the user interface.
     * @param storage To update storage with marked status.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IllegalParamException {
        try {
            Task done = list.getTask(index);
            done.setDone();
            storage.save(list);
            ui.showMessage("Thats sick! Great work, marked as done!\n" + done);
        } catch (IllegalParamException e) {
            throw new IllegalParamException(e.getMessage() + " Unable to mark duke.command.task!");
        }
    }
}
