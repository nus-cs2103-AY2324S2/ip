package chipchat.action;

import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

/**
 * Represents an executable action that will perform operations relating to tasks.
 */
public abstract class Action {

    /**
     * Executes the action.
     *
     * @param tasks task list to perform operations on
     * @param ui ui of application
     * @param storage storage of application
     */
    public abstract void run(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this action will exit from the application or not
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
