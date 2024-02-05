public class UnMarkCommand extends Command {
    private int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = tasks.unMark(index);
        ui.showUnmarkTask(task);
    }
    
}
