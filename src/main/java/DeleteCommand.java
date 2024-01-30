public class DeleteCommand extends Command {
    private final int TASK_NUM;

    public DeleteCommand(String num) {
        this.TASK_NUM = Integer.parseInt(num);
    }

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) {
        Task deletedTask = tasksList.get(TASK_NUM);
        tasksList.deleteFromTaskList(TASK_NUM);
        ui.showDeleteTaskMessage(deletedTask);
        storage.saveToFile(tasksList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}