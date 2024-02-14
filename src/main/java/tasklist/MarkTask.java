package tasklist;

import java.util.ArrayList;
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
    public static String execMarkTask(String input, List<List<? extends Task>> taskList) {
        StringBuilder response = new StringBuilder();
        String[] wordPartition = input.split(" ");
        int idxToGetType = 0;
        String action = wordPartition[idxToGetType];

        try {
            int idxToGetTaskType = 1;
            String taskType = wordPartition[idxToGetTaskType].toUpperCase();
            boolean isEvent = taskType.equals("EVENT");
            boolean isDeadline = taskType.equals("DEADLINE");
            boolean isTodo = taskType.equals("TODO");
            if (!isTodo && !isDeadline && !isEvent) {
                throw new TaylorException("<TaskType> only accepts EVENT/DEADLINE/TODO");
            }

            int idxToGetTaskNo = 2;
            int retrieverNum = Integer.parseInt(wordPartition[idxToGetTaskNo]) - 1;

            if (retrieverNum < 0 || retrieverNum >= taskList.size()) {
                throw new TaylorException("Invalid task number");
            }

            List<? extends Task> listToEdit = new ArrayList<>();
            if (isDeadline) {
                int deadlineListIdx = 0;
                listToEdit = taskList.get(deadlineListIdx);
            } else if (isEvent) {
                int eventListIdx = 1;
                listToEdit = taskList.get(eventListIdx);
            } else if (isTodo) {
                int todoListIdx = 2;
                listToEdit = taskList.get(todoListIdx);
            } else {
                assert false : "Program should not run here";
            }

            if (action.equals("mark")) {
                listToEdit.get(retrieverNum).markIt();
                response.append("Nice! I've marked this task as done:\n");
                response.append(listToEdit.get(retrieverNum));
            } else if (action.equals("unmark")) {
                listToEdit.get(retrieverNum).unMark();
                response.append("OK, I've marked this task as not done yet:\n");
                response.append(listToEdit.get(retrieverNum));
            } else {
                throw new TaylorException("Invalid command -  Only use mark/unmark");
            }
        } catch (Exception err) {
            throw new TaylorException("Please ensure the following format: MARK/UNMARK <TaskType> <TaskNumber>");
        }
        return response.toString();
    }
}
