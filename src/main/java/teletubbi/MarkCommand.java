package teletubbi;

/**
 * Represents the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the marking of a task.
     *
     * @param taskList TaskList.
     * @param ui User interface.
     * @param storage Storage to store TaskList content.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = taskList.get(index - 1);
            setExit(false);
            return t.markAsDone();
        } catch (TeletubbiException e) {
            return e.getMessage();
        }
    }
}
