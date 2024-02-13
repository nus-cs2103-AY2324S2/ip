public class ListCommand extends Command {
    public ListCommand() {
        super();
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
    public  boolean isExit() {
        return false;
    }
}
