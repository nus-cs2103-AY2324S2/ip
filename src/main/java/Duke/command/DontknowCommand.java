package Duke.command;
import Duke.*;
import Duke.task.*;
public class DontknowCommand extends Command {
    public void execute(TaskList tsks, Ui ui, Storage storage){
        Ui.print_message("OOPS!!! I'm sorry, but I don't know what that means");
    }
    public boolean isExit(){
        return false;
    }
}
