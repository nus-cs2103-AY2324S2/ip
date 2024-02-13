package tasklist;

import java.util.ArrayList;
import java.util.List;

import exceptions.TaylorException;
import tasks.Task;


/**
 * Find a task by searching for a keyword
 */
public class FindTask {
    private static final int SPLIT_FIRST_WHITESPACE = 2;
    private static final int KEYWORD = 1;
    private static final int INDEXING = 1;
    /**
     * No constructor needed
     */
    private FindTask() {
        throw new AssertionError("Constructor is not allowed");
        // assert false : "Execution should never reach this point!";
    }

    /**
     * Execute finder using keyword
     * @param input User input
     * @param taskList ArrayList of Tasks
     */
    public static String exec(String input, List<Task> taskList) {
        StringBuilder response = new StringBuilder();
        String[] parts = input.split(" ", SPLIT_FIRST_WHITESPACE);
        boolean isKeywordEmpty = parts[KEYWORD].trim().isBlank();
        if (isKeywordEmpty) {
            throw new TaylorException("The description of the task is empty.");
        }
        String keyword = parts[KEYWORD];
        List<Task> result = new ArrayList<>();
        for (Task tsk : taskList) {
            if (tsk.getExecute().contains(keyword)) {
                result.add(tsk);
            }
        }

        if (result.isEmpty()) {
            response.append("No task found").append("\n");
        } else {
            int pos = INDEXING;
            for (Task tsk : result) {
                response.append(pos++).append(". ").append(tsk).append("\n");
            }
        }
        return response.toString();
    }
}
