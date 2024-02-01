package action;
import handler.PrintHandler;
import handler.DataHandler;
public class ListAction implements Action{
    public ListAction() {}
    @Override
    public void execute() {
        String[] data = DataHandler.instance.getData();
        PrintHandler.instance.printNumberedDivider(data);
    }
}
