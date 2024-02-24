package johnny.commands;

import johnny.storage.Storage;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

/**
 * Controls what happens when a user exits chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Executes the process of a ExitCommand.
     * Ui shows exit response.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui to print responses.
     * @param storage Storage for data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnd();
    }

    /**
     * Exits so chatbot stops running.
     *
     * @return True so the loop stops running.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
