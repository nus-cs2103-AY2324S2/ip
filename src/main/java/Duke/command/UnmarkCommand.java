package Duke.command;
import Duke.*;

public class UnmarkCommand extends Command {
    private int unmarkIndex;
    public UnmarkCommand(int index) {
        this.unmarkIndex = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String unmarkedTaskInfo = taskList.unmark(unmarkIndex);
        String replyMessage = "OK, I've marked this task as not done yet:\n  " + unmarkedTaskInfo;
        Ui.print_message(replyMessage);
        storage.writeDisk(taskList.accessList());
        return replyMessage;
    }
    public boolean isExit() {
        return false;
    }

}
