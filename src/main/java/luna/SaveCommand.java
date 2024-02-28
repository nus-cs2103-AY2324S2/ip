package luna;

/**
 * Represents a save command. Saves all the task from the task list to the storage
 */
public class SaveCommand extends Command {

    public SaveCommand() {
        super(CommandType.SAVE);
    }

    /**
     * Clears any preexisting task in the storage.
     * Copied all the task list to the storage
     *
     * @param tl the task list
     * @param ui the program ui
     * @param storage list file storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        storage.clearFile();
        storage.appendList(tl);
        ui.shiftedPrint("Save tasks to files");

    }
}
