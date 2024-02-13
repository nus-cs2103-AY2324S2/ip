package tasklist;

import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

/**
 * Mark/Unmark task as done
 */
public class MarkTask {
    /**
     * No constructor needed
     */
    private MarkTask() {
        throw new AssertionError("Constructor is not allowed");
        // assert false : "Execution should never reach this point!";
    }

    /**
     * Execute marking/unmarking
     * @param input : to mark or unmark and which one?
     * @param taskList
     */
    public static String execMarkTask(String input, List<Task> taskList) {
        StringBuilder response = new StringBuilder();
        String[] wordPartition = input.split(" ");
        int idxToGetTask = 0;
        String action = wordPartition[idxToGetTask];

        try {
            int idxToGetTaskNo = 1;
            int retrieverNum = Integer.parseInt(wordPartition[idxToGetTaskNo]) - 1;

            if (retrieverNum < 0 || retrieverNum >= taskList.size()) {
                throw new TaylorException("Invalid task number");
            }

            if (action.equals("mark")) {
                taskList.get(retrieverNum).markIt();
                response.append("Nice! I've marked this task as done:\n");
                response.append(taskList.get(retrieverNum));
            } else if (action.equals("unmark")) {
                taskList.get(retrieverNum).unMark();
                response.append("OK, I've marked this task as not done yet:\n");
                response.append(taskList.get(retrieverNum));
            } else {
                throw new TaylorException("Invalid command -  Only use mark/unmark");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Please insert task number!");
        }
        return response.toString();
    }
}
