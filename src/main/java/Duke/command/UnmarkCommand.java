package Duke.command;
import Duke.*;

public class UnmarkCommand extends Command {
    private int unmarkIndex;
    public UnmarkCommand(int index) {
        this.unmarkIndex = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String unmarkedTaskInfo = taskList.unmark(unmarkIndex);
        Ui.print_message("OK, I've marked this task as not done yet:\n  " + unmarkedTaskInfo);
        storage.writeDisk(taskList.accessList());
    }
    public boolean isExit() {
        return false;
    }

}
