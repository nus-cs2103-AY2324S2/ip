package reacher.command;

import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;

/**
 * Command that shows user all tasks in list.
 */
public class ListCommand extends Command{
    /**
     * Execute command by printing the tasks in Tasks.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Local file storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTasks());
    }
    @Override
    public boolean equals(Object object){
        return object instanceof ListCommand;
    }
}
