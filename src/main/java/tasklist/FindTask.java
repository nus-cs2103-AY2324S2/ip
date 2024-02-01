package tasklist;

import java.util.ArrayList;
import java.util.List;

import exceptions.TaylorException;
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
     * @param input User input
     * @param taskList ArrayList of Tasks
     */
    public static void exec(String input, List<Task> taskList) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isBlank()) {
            throw new TaylorException("The description of the task is empty.");
        }
        String keyword = parts[1];
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
