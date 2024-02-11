package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>ExitCommand</code> object represents the command to exit the program.
 */
public class ExitCommand extends Command {
    private static final String RESULT_MSG_FORMATTED = "Bye. Hope to see you again soon.";
    /**
     * @inheritDoc This implementation prints a farewell message after saving changes to the local file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws JadeException {
        storage.saveChange(taskList);
        return RESULT_MSG_FORMATTED;
    }

    /**
     * @inheritDoc The ExitCommand indicates the exit of the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
