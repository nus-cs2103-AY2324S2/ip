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

    /**
     * Constructor that specifies the keyword to search for in the list of Tasks.
     *
     * @param keyword The keyword to search for in the list of Tasks.
     */
    public FindAction(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the list of Tasks that match the keyword and returns a String representation of the list.
     *
     * @return A String representation of the list of Tasks that match the keyword.
     */
    @Override
    public String execute() {
        assert keyword != null : "keyword should not be null";
        ArrayList<Task> result = DataHandler.findTasks(keyword);
        return PrintHandler.printNumberedDivider(result);
    }
}
