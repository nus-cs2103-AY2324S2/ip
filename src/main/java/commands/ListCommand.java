package commands;

import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * Lists out the tasks in the TaskList.
 */
public class ListCommand extends Command {
    /**
     * Lists out the task in the TaskList and show the user.
     *
     * @param tasks All the tasks of the user.
     * @param ui Gives reply to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String list = tasks.list();
        return ui.showList(list);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
