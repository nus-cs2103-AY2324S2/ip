import java.io.IOException;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int i) {
        this.taskNumber = i;
    }
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.showMark();
        try {
            list.markDone(taskNumber);
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
