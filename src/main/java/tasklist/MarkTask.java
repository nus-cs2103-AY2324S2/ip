package tasklist;

import java.util.List;

import exceptions.TaylorException;
import taskhelper.CheckValid;
import taskhelper.IterateList;
import taskhelper.WordsSplit;
import tasks.Task;
import ui.Ui;

/**
 * Mark/Unmark task as done
 */
public class MarkTask {
    /**
     * No constructor needed
     */
    private MarkTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Execute marking/unmarking
     * @param input : to mark or unmark and which one?
     * @param taskList List containing Todo, Deadline and Event
     *                 list of task
     */
    public static String execMarkTask(String input, List<List<Task>> taskList) {
        StringBuilder response = new StringBuilder();
        String[] wordPartition = WordsSplit.separateWords(input, " ", false);
        int idxToGetType = 0;
        String action = WordsSplit.getWord(wordPartition, idxToGetType).toUpperCase();

        try {
            int idxToGetTaskType = 1;
            String taskType = WordsSplit.getWord(wordPartition, idxToGetTaskType).toUpperCase();
            CheckValid.checkValidType(taskType);

            int idxToGetTaskNo = 2;
            int retrieverNum = Integer.parseInt(WordsSplit.getWord(wordPartition, idxToGetTaskNo)) - 1;

            List<Task> listToEdit = IterateList.retrieveList(taskList, taskType);
            CheckValid.checkValidNum(retrieverNum, listToEdit);

            Ui.markTask(response, listToEdit, retrieverNum, action);
        } catch (Exception err) {
            throw new TaylorException("Please ensure the following format: MARK/UNMARK <TaskType> <TaskNumber>");
        }
        return response.toString();
    }
}
