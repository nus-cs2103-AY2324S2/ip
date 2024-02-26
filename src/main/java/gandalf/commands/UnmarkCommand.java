package gandalf;

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
    public void execute() throws GandalfException {
        tasks.unmark(taskNumber);
        ui.unmark();
        storage.store(tasks.getList());
    }
}
