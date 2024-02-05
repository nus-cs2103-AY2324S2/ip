public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = tasks.mark(index);
        ui.showMarkTask(task);
    }
    
}
