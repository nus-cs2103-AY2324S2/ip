package luna;


/**
 * Represents a find command which shows a list with the task that matches a keyword
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Construct a Command to have find command type and assign a keyword
     *
     * @param kw keyword
     */
    public FindCommand(String kw) {
        super(CommandType.FIND);
        this.keyword = kw;
    }

    /**
     * Creates a new list with the task that contains the keyword.
     * Then prompts a showlist command to that new list.
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        TaskList filteredList = new TaskList();
        for (int i = 0; i < tl.getSize(); i++) {
            ListEntry ent = getTaskList().getEntry(i);
            if (ent.hasKeyword(keyword)) {
                filteredList.add(ent);
            }
        }
        ui.showList(filteredList);
    }
}
