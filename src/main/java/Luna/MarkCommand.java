package Luna;

/**
 * Represents a mark command. Marks a task in the task list
 */
public class MarkCommand extends Command{
    int index;

    public MarkCommand(int index) {
        super(Command.CommandType.MARK);
        this.index = index;
    }

    /**
     * Marks a task as done in the task list, setting its check to true
     * Prompts an InvalidCommand if the referenced index is invalid
     * Then shows the list
     *
     * @param tl the task list
     * @param ui the program ui
     * @param storage list file storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        if (tl.isValidIndex(index)) {
            tl.mark(index);
        } else {
            new InvalidCommand("index not in range of list").execute(tl, ui, storage);
        }
        ui.showList(tl);
    }
}
