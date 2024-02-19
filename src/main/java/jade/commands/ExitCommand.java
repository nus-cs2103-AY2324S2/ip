package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>ExitCommand</code> object represents the command to exit the program.
 */
public class ExitCommand extends Command {
    private static final String RESULT_MSG_FORMATTED = "Thank you for using Jade, have a great day. :D";
    /**
     * {@inheritDoc}
     * Prints a farewell message after saving changes to the local file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws JadeException {
        storage.saveChange(taskList);
        return RESULT_MSG_FORMATTED;
    }

    /**
     * {@inheritDoc}
     * Indicates that the program is exiting.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
