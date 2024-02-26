package gandalf;

/**
 * Calls the TaskList object's mark() method to mark the task at the specified index
 */
public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(String commandName, TaskList tasks, Storage storage, Ui ui, int taskNumber) {
        super(commandName, tasks, storage, ui);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute() throws GandalfException {
        tasks.mark(taskNumber);
        ui.mark();
        storage.store(tasks.getList());
    }
}
