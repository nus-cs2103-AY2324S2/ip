package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>UnmarkCommand</code> object represents the command to mark a task as not done yet.
 */
public class UnmarkCommand extends Command {
    private static final String RESULT_MSG_FORMATTED = "Nice, I've marked this task "
            + "as not done yet:\n\t  %s";
    private static final String ERR_MSG = "Please input a valid number to unmark done.";
    private final int index; // the index of the task to be unmarked

    /**
     * Class constructor specifying the index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Prints an unmark message after the task is unmarked.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws JadeException {
        if (index <= 0 || index > taskList.size()) {
            throw new JadeException(ERR_MSG);
        }
        taskList.unmark(index - 1);
        String result = String.format(RESULT_MSG_FORMATTED, taskList.get(index - 1));
        storage.saveChange(taskList);
        return result;
    }

    /**
     * {@inheritDoc}
     * Indicates that the program is not exiting.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
