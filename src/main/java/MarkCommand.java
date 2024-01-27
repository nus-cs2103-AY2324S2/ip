/**
 * Represents mark commands.
 */
public class MarkCommand extends Command {
    private int index;
    private TaskList tasks;

    public MarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public TaskList execute(Ui ui) {
        tasks.markTask(index);
        System.out.println(ui.replyMarkCommand());
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
