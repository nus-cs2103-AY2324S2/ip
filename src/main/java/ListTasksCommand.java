public class ListTasksCommand extends Command{
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskListContents(taskList.getTaskStore());
    }
    public boolean isExit() {
        return false;
    }
}
