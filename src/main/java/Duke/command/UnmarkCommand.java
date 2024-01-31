package Duke.command;
import Duke.*;
import Duke.task.*;
import Duke.*;
public class UnmarkCommand extends Command {
    private int unmarkIndex;
    public UnmarkCommand(int index){
        this.unmarkIndex = index;
    }
    public void execute(TaskList tsks, Ui ui, Storage storage){
        String temp = tsks.unmark(unmarkIndex);
        Ui.print_message("OK, I've marked this task as not done yet:\n  "+temp);
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }

}
