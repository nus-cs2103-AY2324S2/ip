package Duke.command;
import Duke.*;
import Duke.task.*;
public class DeleteCommand extends Command {
    private int deleteIndex;
    public DeleteCommand(int index){
        this.deleteIndex = index;
    }
    public void execute(TaskList tsks, Ui ui, Storage storage){
        String temp = tsks.delete(deleteIndex);
        Ui.print_message("Noted. I've removed this task:\n  "
                +temp+"\n "+
                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }
}