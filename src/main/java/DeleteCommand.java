import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int i) {
        this.taskNumber = i;
    }
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showDelete();
        try {
            list.deleteTask(taskNumber);
            s.storeTaskList(list.getList());
        } catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.listTasks(list.getList());
    }

    public boolean isExit() {
        return false;
    }
}
