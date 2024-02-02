package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.ui.Ui;
import jade.storage.Storage;

/**
 * The <code>ExitCommand</code> object represents the command to exit the program.
 */
public class ExitCommand extends Command{
    /**
     * @inheritDoc
     * This implementation prints a farewell message after saving changes to the local file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JadeException {
        storage.saveChange(taskList);
        ui.printMessage("\tBye. Hope to see you again soon.");
    }

    /**
     * @inheritDoc
     * The ExitCommand indicates the exit of the program.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
