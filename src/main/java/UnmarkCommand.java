public class UnmarkCommand extends Command{
    private int taskNum;
    public UnmarkCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsNotDone(taskNum);
    }
}
