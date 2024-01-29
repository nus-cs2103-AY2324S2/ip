package action;

import java.util.List;

import task.Task;
import util.PrintUtil;
import util.TaskList;

/**
 * Represents an action to find tasks based on a given search string.
 */
public class Find implements Action {
    private final String s;

    /**
     * Constructs a Find object with the specified search string.
     *
     * @param s The search string used to find tasks.
     */
    public Find(String s) {
        this.s = s;
    }

    /**
     * Executes the find action by searching for tasks that match the search string
     * and printing the results.
     */
    @Override
    public void execute() {
        List<Task> list = TaskList.find(s);
        PrintUtil.printList(list);
    }
}
