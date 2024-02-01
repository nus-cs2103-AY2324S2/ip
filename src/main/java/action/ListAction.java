package action;
import action.task.Task;
import handler.PrintHandler;
import handler.DataHandler;
public class ListAction implements Action{
    public ListAction() {}
    @Override
    public void execute() {
        Task[] data = DataHandler.instance.getData();
        PrintHandler.instance.print("Here is your list of tasks:");
        PrintHandler.instance.printNumberedDivider(data);
    }
}
