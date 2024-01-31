import java.util.ArrayList;

public class ListCommand extends Command{
    public ListCommand(){
        super(0);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
        String out = tl.showList();
        ui.showMessage(out);
    }
}
