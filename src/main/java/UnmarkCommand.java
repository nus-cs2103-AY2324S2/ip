public class UnmarkCommand extends Command {
    private int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showUnmarked(taskList.unmarkTask(taskId));
        storage.save(taskList);
    }

}
