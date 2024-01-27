/**
 * Represents unmark commands.
 */
public class UnmarkCommand extends Command {
    private int index;
    private TaskList tasks;

    public UnmarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public TaskList execute(Ui ui) {
        tasks.unmarkTask(index);
        System.out.println(ui.replyUnmarkCommand());
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
