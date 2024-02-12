package Duke.command;
import Duke.*;

public class MarkCommand extends Command {
    private final int markIndex;
    public MarkCommand(int index) {
        this.markIndex = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String markedTaskInfo = taskList.mark(markIndex);
        Ui.print_message("Nice! I've marked this task as done:\n  " + markedTaskInfo);
        storage.writeDisk(taskList.accessList());
    }
    public boolean isExit() {
        return false;
    }
}
