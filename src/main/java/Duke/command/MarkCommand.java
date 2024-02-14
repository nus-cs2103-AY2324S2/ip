package Duke.command;
import Duke.*;

public class MarkCommand extends Command {
    private final int markIndex;
    public MarkCommand(int index) {
        this.markIndex = index;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String markedTaskInfo = taskList.mark(markIndex);
        String replyMessage = "Nice! I've marked this task as done:\n  " + markedTaskInfo;
        Ui.print_message(replyMessage);
        storage.writeDisk(taskList.accessList());
        return replyMessage;
    }
    public boolean isExit() {
        return false;
    }
}
