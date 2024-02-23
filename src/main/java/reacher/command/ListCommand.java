package reacher.command;

import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;
import reacher.ui.MainWindow;

/**
 * Command that shows user all tasks in list.
 */
public class ListCommand extends Command {
    /**
     * Execute command by printing the tasks in Tasks.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Local file storage.
     * @return
     */
    @Override
    public String execute(String input, TaskList tasks, Ui ui, Storage storage) {
        return ui.listToString(tasks.getTasks());

    }
    @Override
    public boolean equals(Object object){
        return object instanceof ListCommand;
    }
}
