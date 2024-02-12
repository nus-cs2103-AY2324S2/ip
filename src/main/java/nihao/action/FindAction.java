package nihao.action;

import nihao.action.task.Task;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

import java.util.ArrayList;

public class FindAction implements Action{
    private String keyword;
    public FindAction(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute() throws Exception {
        ArrayList<Task> result = DataHandler.findTasks(keyword);
        PrintHandler.printNumberedDivider(result);
    }
}
