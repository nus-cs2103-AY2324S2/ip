package Luna;

/**
 * Represents a list command which lists out the task in the task list
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Prompts the ui to display the all the entries in the list
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        ui.showList(tl);
    }
}
