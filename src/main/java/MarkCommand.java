public class MarkCommand extends Command {
    private int taskNum;
    public MarkCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(taskNum);
    }
}
