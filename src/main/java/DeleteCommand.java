public class DeleteCommand extends Command {
    private int taskNum;
    public DeleteCommand(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNum);
    }
}
