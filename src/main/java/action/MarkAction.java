package action;
import handler.PrintHandler;
import handler.DataHandler;
public class MarkAction implements Action{
    private int index;
    public MarkAction(String index) {
        this.index = Integer.parseInt(index);
    }
    @Override
    public void execute() {

    }
}
