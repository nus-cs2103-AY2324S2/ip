package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IllegalParamException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to unmark a specified task.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a unmark command.
     *
     * @param index Task index to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command, marking task at specified index as not done.
     * Also displays messages to user.
     *
     * @param list TaskList object containing current tasks.
     * @param ui To send instructions on how to update the user interface.
     * @param storage To update storage with not marked status.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IllegalParamException {
        try {
            Task notDone = list.getTask(this.index);
            notDone.setNotDone();
            storage.save(list);
            ui.showMessage("Awh why uncheck me :( Its ok, it is what it is!\n" + notDone);
        } catch (IllegalParamException e) {
            throw new IllegalParamException(e.getMessage() + " Unable to unmark duke.command.task!");
        }
    }
}