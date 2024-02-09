package duke.command;

import duke.Storage;
import duke.exceptions.IllegalParamException;
import duke.task.Task;
import duke.task.TaskList;
/**
 * Represents a command to mark a specified task.
 */
public class MarkCommand extends Command {
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
     * Also updates response attribute.
     *
     * @param list TaskList object containing current tasks.
     * @param storage To update storage with marked status.
     */
    @Override
    public void execute(TaskList list, Storage storage) throws IllegalParamException {
        try {
            Task done = list.getTask(index);
            done.setDone();
            storage.save(list);

            super.setResponse("Thats sick! Great work, marked as done!\n" + done);

        } catch (IllegalParamException e) {
            throw new IllegalParamException(e.getMessage() + " Unable to mark duke.command.task!");
        }
    }
}
