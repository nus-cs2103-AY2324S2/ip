package gandalf.commands;

import gandalf.GandalfException;
import gandalf.Storage;
import gandalf.TaskList;
import gandalf.Ui;
import gandalf.tasktypes.Task;

/**
 * Deletes the task at the specified index and stores the modified list.
 */
public class DeleteCommand extends Command {

    private String index;
    private Task taskName;
    public DeleteCommand(String commandName, TaskList tasks, Storage storage, Ui ui, String index) {
        super(commandName, tasks, storage, ui, index);
        this.index = otherInputs[0];
    }

    public String execute() throws GandalfException {
        tasks.delete(index);
        storage.store(tasks.getList());
        return ui.delete();
    }
}
