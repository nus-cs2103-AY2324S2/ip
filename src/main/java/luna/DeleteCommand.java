package luna;

/**
 * Represents a delete command which deletes a task from the task list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Construct the command object and assign an index variable
     *
     * @param index task name
     */
    public DeleteCommand(int index) {
        super(Command.CommandType.DELETE);
        this.index = index;
    }

    /**
     * Deletes an entry from the task list if index given is valid.
     * Prompts a InvalidCommand if invalid index
     *
     * @param tl the task list
     * @param ui the program ui
     * @param storage list file storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        if (tl.isValidIndex(index)) {
            tl.delete(index);
            ui.shiftedPrint("Deleted a task from the list");
            ui.showList(tl);
        } else {
            new InvalidCommand("index not in range of list").execute(tl, ui, storage);
        }
    }
}
