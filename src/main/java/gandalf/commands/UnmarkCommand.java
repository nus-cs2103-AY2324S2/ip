package gandalf.commands;

import gandalf.GandalfException;
import gandalf.Storage;
import gandalf.TaskList;
import gandalf.Ui;

/**
 * Calls the TaskList object's unmark() method to unmark the task at the specified index.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(String commandName, TaskList tasks, Storage storage, Ui ui, int taskNumber) {
        super(commandName, tasks, storage, ui);
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute() throws GandalfException {
        storage.store(tasks.getList());
        return ui.unmark() + "\n" + tasks.unmark(taskNumber);
    }
}
