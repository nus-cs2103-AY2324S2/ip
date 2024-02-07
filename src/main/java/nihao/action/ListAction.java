package nihao.action;
import nihao.action.task.Task;
import nihao.handler.PrintHandler;
import nihao.handler.DataHandler;

import java.util.ArrayList;

public class ListAction implements Action{
    public ListAction() {}
    @Override
    public void execute() {
        ArrayList<Task> data = DataHandler.getData();
        PrintHandler.print("Here is your list of tasks:");
        PrintHandler.printNumberedDivider(data);
    }
}
