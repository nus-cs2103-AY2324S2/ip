package Luna;

/**
 * Represents a load command loads any task from the storage to the task list
 */
public class LoadCommand extends Command {

    public LoadCommand() {
        super(CommandType.LOAD);
    }

    /**
     * Clears any preexisting task in the task list.
     * Adds all the task if any from the storage to the task list
     *
     * @param tl the task list
     * @param ui the program ui
     * @param storage list file storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        tl.clear();
        storage.loadList(tl);
        ui.shiftedPrint("Loaded tasks from files");
    }
}
