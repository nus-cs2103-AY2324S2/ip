package chipchat.action;

import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

/**
 * Represents an executable action that will exit this application when executed.
 */
public final class Bye extends Action {

    /**
     * Simple constructor.
     */
    public Bye() {
    }

    /**
     * Performs the saving and UI outputs before exiting the application.
     *
     * @param tasks task list to perform operations on
     * @param ui ui of application
     * @param storage storage of application
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
    }

    /**
     * Returns whether this action will exit from the application or not
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
