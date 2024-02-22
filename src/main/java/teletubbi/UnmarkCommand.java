package teletubbi;

/**
 * Represents the command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmarking of a task.
     * @param taskList Tasklist containing task.
     * @param ui User interface.
     * @param storage Storage to store Tasklist content.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = taskList.get(index - 1);
            setExit(false);
            return t.unmark();
        } catch (TeletubbiException e) {
            return e.getMessage();
        }
    }
}
