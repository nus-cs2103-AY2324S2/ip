package reacher;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.print("Bye!");
    }
    @Override
    public boolean isExit(){
        return true;
    }
}
