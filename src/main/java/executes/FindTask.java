package executes;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;


/**
 * Find a task by searching for a keyword
 */
public class FindTask {
    /**
     * No constructor needed
     */
    private FindTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Execute finder using keyword
     * @param keyword User input
     * @param taskList ArrayList of Tasks
     */
    public static void exec(String keyword, List<Task> taskList) {
        List<Task> result = new ArrayList<>();
        for (Task tsk : taskList) {
            if (tsk.getExecute().contains(keyword)) {
                result.add(tsk);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No task found");
        } else {
            int pos = 1;
            for (Task tsk : result) {
                System.out.println(pos++ + "." + tsk);
            }
        }
    }
}
