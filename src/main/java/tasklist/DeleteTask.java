package tasklist;

import java.util.ArrayList;
import java.util.List;

import exceptions.TaylorException;
import helper.WordsSplit;
import tasks.Task;

/**
 * To delete a task
 */
public class DeleteTask {
    /**
     * No constructor needed
     */
    private DeleteTask() {
        throw new AssertionError("Constructor is not allowed");
        // assert false : "Execution should never reach this point!";
    }

    /**
     * Execute Deleting tasks
     *
     * @param input    : User input
     * @param taskList
     * @throws TaylorException
     */
    // delete event 1
    public static String execDeleteTask(String input, List<List<Task>> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
        String[] wordPartition = WordsSplit.separateWords(input, " ", false);
        try {
            int idxToGetTaskType = 1;
            String taskType = WordsSplit.getWord(wordPartition, idxToGetTaskType).toUpperCase();

            boolean isEvent = taskType.equals("EVENT");
            boolean isDeadline = taskType.equals("DEADLINE");
            boolean isTodo = taskType.equals("TODO");
            if (!isTodo && !isDeadline && !isEvent) {
                throw new TaylorException("<TaskType> only accepts EVENT/DEADLINE/TODO");
            }

            int idxToGetTaskNo = 2;
            int noToDelete = Integer.parseInt(wordPartition[idxToGetTaskNo]);

            List<? extends Task> listToEdit = new ArrayList<>();
            String editList = null;
            if (isDeadline) {
                int deadlineListIdx = 0;
                listToEdit = taskList.get(deadlineListIdx);
                editList = "Deadline";
            } else if (isEvent) {
                int eventListIdx = 1;
                listToEdit = taskList.get(eventListIdx);
                editList = "Event";
            } else if (isTodo) {
                int todoListIdx = 2;
                listToEdit = taskList.get(todoListIdx);
                editList = "ToDo";
            } else {
                assert false : "Program should not run here";
            }

            if (noToDelete < 0 || noToDelete > listToEdit.size()) {
                throw new TaylorException("Invalid task number");
            }

            int idx = noToDelete - 1;
            Task taskRemoved = listToEdit.get(idx);
            listToEdit.remove(idx);
            response.append("Noted. I've removed this tasks:\n");
            response.append(taskRemoved).append("\n");
            response.append("Now you have ").append(listToEdit.size())
                    .append(" tasks in the ").append(editList).append("list.\n");

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException err) {
            throw new TaylorException("Please ensure the following format: DELETE <TaskType> <TaskNumber>");
        }
        return response.toString();
    }
}
