package duke;

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
     * @param tasks Tasklist containing task.
     * @param ui User interface.
     * @param storage Storage to store Tasklist content.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = tasks.get(index - 1);
            t.unmark();
            setExit(false);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }
}