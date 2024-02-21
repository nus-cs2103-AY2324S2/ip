package tasklist;

import java.util.List;

import exceptions.TaylorException;
import taskhelper.CheckValid;
import taskhelper.IterateList;
import taskhelper.WordsSplit;
import tasks.Task;
import ui.Ui;

/**
 * Deletes a task.
 */
public class DeleteTask {
    /**
     * No constructor needed.
     */
    private DeleteTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Executes tasks deletion.
     *
     * @param input    : User input
     * @param taskList List of tasks list
     * @throws TaylorException Invalid user command
     */
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
            throw new TaylorException("\n"
                    + "In the depths of twilight's hue,\n"
                    + "I'm lost in thoughts of me and you.\n"
                    + "==============================\n"
                    + "Please ensure the following format: DELETE <TaskType> <TaskNumber>");
        }
        return response.toString();
    }
}
