package Duke.command;
import Duke.*;
import Duke.task.*;
public class ListCommand extends Command {
    public void execute(TaskList tsks, Ui ui, Storage storage){
        Ui.print_message(tsks.toString());
    }
    public boolean isExit(){
        return false;
    }
}
