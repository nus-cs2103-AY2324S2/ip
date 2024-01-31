package Duke.command;
import Duke.*;
import Duke.task.*;
public class ExitCommand extends Command {
    public void execute(TaskList tsks, Ui ui, Storage storage){
        Ui.print_message("Bye");
    }
    public boolean isExit(){
        return true;
    }
}