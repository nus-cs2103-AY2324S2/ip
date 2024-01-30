public class TodoCommand extends Command {
    private final String DETAIL;
    public TodoCommand(String taskDetails) {
        this.DETAIL = taskDetails;
    }

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) throws ExceptionDuke{
        Task todo = new Todo(DETAIL);
        tasksList.addToTaskList(todo);
        storage.saveToFile(tasksList.getList());
        ui.showAddTaskMessage(todo);
        ui.showTotalCountMessage(tasksList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}