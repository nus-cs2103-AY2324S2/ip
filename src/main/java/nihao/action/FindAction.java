package nihao.action;

import java.util.ArrayList;

import nihao.action.task.Task;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

/**
 * Represents an Action that looks up the saved list of Tasks.
 */
public class FindAction implements Action {
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
