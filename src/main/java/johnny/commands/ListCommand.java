package johnny.commands;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when TaskList is listed.
 */
public class ListCommand extends Command {

    /**
     * Executes the process of a ListCommand.
     * Ui prints all Tasks in TaskList.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     * @throws JohnnyException Ignore.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        ui.showList(tasks);
    }

    /**
     * Returns False so chatbot can continue running.
     *
     * @return False so the loop keeps running.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
