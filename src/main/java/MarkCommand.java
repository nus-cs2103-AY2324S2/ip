public class MarkCommand extends Command {
    private int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showMarked(taskList.markTask(taskId));
        storage.save(taskList);
    }


}
