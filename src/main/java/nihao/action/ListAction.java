package nihao.action;
import nihao.action.task.Task;
import nihao.handler.PrintHandler;
import nihao.handler.DataHandler;

import java.util.ArrayList;

/**
 * Represents the list action.
 */
public class ListAction implements Action{
    /**
     * Prints the tasks list.
     */
    @Override
    public String execute() {
        ArrayList<Task> data = DataHandler.getData();
        return PrintHandler.print("Here is your list of tasks:") + PrintHandler.printNumberedDivider(data);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListAction;
    }
}
