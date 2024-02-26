package gandalf;

/**
 * Deletes the task at the specified index and stores the modified list.
 */
public class DeleteCommand extends Command {

    private String index;
    public DeleteCommand(String commandName, TaskList tasks, Storage storage, Ui ui, String index) {
        super(commandName, tasks, storage, ui, index);
        this.index = otherInputs[0];
    }

    public void execute() throws GandalfException {
        tasks.delete(index);
        storage.store(tasks.getList());
    }
}
