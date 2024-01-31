package Duke.command;
import Duke.*;
import Duke.task.*;
public class MarkCommand extends Command {
    private int markIndex;
    public MarkCommand(int index){
        this.markIndex = index;
    }
    public void execute(TaskList tsks, Ui ui, Storage storage){
        String temp = tsks.mark(markIndex);
        Ui.print_message("Nice! I've marked this task as done:\n  "+ temp);
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }
}