package duke.command;

import duke.Storage;
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
     * Updates response.
     *
     * @param list TaskList object containing current tasks.
     * @param storage To update storage with not marked status.
     */
    @Override
    public void execute(TaskList list, Storage storage) throws IllegalParamException {
        try {
            Task notDone = list.getTask(this.index);
            notDone.setNotDone();
            storage.save(list);

            super.setResponse("Awh why uncheck me :( Its ok, it is what it is!\n" + notDone);
        } catch (IllegalParamException e) {
            throw new IllegalParamException(e.getMessage() + " Unable to unmark task!");
        }
    }
}
