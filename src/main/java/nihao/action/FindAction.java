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
    public String execute() throws Exception {
        assert keyword != null : "keyword should not be null";
        ArrayList<Task> result = DataHandler.findTasks(keyword);
        return PrintHandler.printNumberedDivider(result);
    }
}
