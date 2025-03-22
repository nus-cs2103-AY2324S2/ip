package action;

import java.util.List;

import task.Task;
import util.PrintUtil;
import util.TaskList;

/**
 * Represents an action to find tasks based on a given search string.
 */
public class Find implements Action {
    private final String[] s;

    /**
     * Constructs a Find object with the specified search string.
     *
     * @param s The search strings used to find tasks.
     */
    public Find(String... s) {
        this.s = s;
    }

    /**
     * Executes the find action by searching for tasks that match the search strings
     * and printing the results.
     *
     * @return A string representing the result of the execution.
     */
    @Override
    public String execute() {
        List<Task> list = TaskList.find(s);
        assert list != null : "Task list cannot be null"; // Assertion to check if the task list is not null
        assert !list.isEmpty() : "Task list cannot be empty"; // Assertion to check if the task list is not empty
        return PrintUtil.printList(list);
    }
}
