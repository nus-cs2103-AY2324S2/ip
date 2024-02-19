package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>MarkCommand</code> object represents the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private static final String RESULT_MSG_FORMATTED = "Nice, I've marked this task as done:\n\t  %s";
    private static final String ERR_MSG = "Please input a valid number to mark done.";
    private final int index; // the index of the task to be marked

    /**
     * Class constructor specifying the index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Prints a mark message after the task is marked.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws JadeException {
        if (index <= 0 || index > taskList.size()) {
            throw new JadeException(ERR_MSG);
        }
        taskList.mark(index - 1);
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
