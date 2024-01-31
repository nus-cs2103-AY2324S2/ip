package Duke.command;
import Duke.*;
import Duke.task.*;
public class AddCommand extends Command {
    private task tsk;
    public AddCommand(task tsk){
        this.tsk = tsk;
    }
    public void execute(TaskList tsks, Ui ui, Storage storage){
        tsks.insert(this.tsk);
        Ui.print_message("Got it. I've added this task:\n  "
                +tsk.toString()+"\n "+
                "Now you have "+tsks.accessNumberTask()+" tasks in the list.");
        storage.writeDisk(tsks.accessList());
    }
    public boolean isExit(){
        return false;
    }
}
