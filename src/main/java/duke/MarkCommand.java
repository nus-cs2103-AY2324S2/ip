package duke;

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
     * @param tasks TaskList.
     * @param ui User interface.
     * @param storage Storage to store TaskList content.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.get(index - 1);
            t.markAsDone();
            setExit(false);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
