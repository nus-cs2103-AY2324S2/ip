package Luna;

/**
 * Represents a delete command which deletes a task from the tasklist
 */
public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        super(Command.CommandType.DELETE);
        this.index = index;
    }

    /**
     * Deletes an entry from the task list if index given is valid.
     * Prompts a InvalidCommand if invalid index
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        if (tl.isValidIndex(index)) {
            tl.delete(index);
        } else {
            new InvalidCommand("index not in range of list").execute(tl, ui, storage);
        }
        ui.showList(tl);
    }
}
