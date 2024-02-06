package duke;

/**
 * Represents the command to add a task.
 */
public class AddCommand extends Command {
    private Task t;
    public AddCommand(Task t) {
        this.t = t;
    }

    /**
     * Executes the adding of a task to the specified tasklist.
     *
     * @param tasks Tasklist to be added to.
     * @param ui User interface.
     * @param storage Storage to save Tasklist content.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.t);
        setExit(false);
    }
}
