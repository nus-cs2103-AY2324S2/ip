package tasklist;

import java.util.List;

import exceptions.TaylorException;
import helper.CheckValid;
import helper.IterateList;
import helper.WordsSplit;
import tasks.Task;
import ui.Ui;

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
     * @param taskList List of tasks list
     * @throws TaylorException Invalid user command
     */
    // delete event 1
    public static String execDeleteTask(String input, List<List<Task>> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
        String[] wordPartition = WordsSplit.separateWords(input, " ", false);
        try {
            int idxToGetTaskType = 1;
            String taskType = WordsSplit.getWord(wordPartition, idxToGetTaskType).toUpperCase();

            CheckValid.checkValidType(taskType);

            int idxToGetTaskNo = 2;
            int noToDelete = Integer.parseInt(wordPartition[idxToGetTaskNo]);

            List<Task> listToEdit = IterateList.retrieveList(taskList, taskType);
            CheckValid.checkValidNum(noToDelete, listToEdit);

            Ui.deleteTask(response, listToEdit, noToDelete);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException err) {
            throw new TaylorException("Please ensure the following format: DELETE <TaskType> <TaskNumber>");
        }
        return response.toString();
    }
}
