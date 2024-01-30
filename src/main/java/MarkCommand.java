public class MarkCommand extends Command{
    private final int TASK_NUM;

    public MarkCommand(String taskNum) {
        this.TASK_NUM = Integer.parseInt(taskNum);
    }

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) throws ExceptionDuke {
        if (TASK_NUM <= 0 | TASK_NUM > tasksList.getList().size()) {
            throw new ExceptionDuke("Invalid task number -.-!");
        }
        tasksList.mark(TASK_NUM);
        Task markedTask = tasksList.get(TASK_NUM);
        ui.showMarkTaskMessage(markedTask);
        storage.saveToFile(tasksList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}