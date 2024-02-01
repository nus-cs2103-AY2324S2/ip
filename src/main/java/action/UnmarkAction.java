package action;
import handler.PrintHandler;
import handler.DataHandler;
public class UnmarkAction implements Action{
    private int index;
    public UnmarkAction(String index) {
        this.index = Integer.parseInt(index);
    }
    @Override
    public void execute() {

    }
}
