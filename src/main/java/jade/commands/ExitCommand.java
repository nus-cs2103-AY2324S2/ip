package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;
import jade.ui.Ui;

/**
 * The <code>ExitCommand</code> object represents the command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * @inheritDoc This implementation prints a farewell message after saving changes to the local file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws JadeException {
        storage.saveChange(taskList);
        String result = "Bye. Hope to see you again soon.";
        ui.printMessage(result);
        return result;
    }

    /**
     * @inheritDoc The ExitCommand indicates the exit of the program.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
