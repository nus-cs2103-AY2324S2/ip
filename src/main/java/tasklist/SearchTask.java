package tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.TaylorException;
import taskhelper.IterateList;
import taskhelper.TaskInsertion;
import taskhelper.WordsSplit;
import tasks.Task;
import ui.Ui;

/**
 * To search tasks based on Date and Time
 */
public class SearchTask {
    /**
     * No constructor needed
     */
    private SearchTask() {
        throw new AssertionError("Constructor is not allowed");
    }
    /**
     * Search Task in ArrayList based on Date and Time
     * @param input Date and Time to search
     * @param taskList ArrayList with Tasks
     */
    public static String execSearchTask(String input, List<List<Task>> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
        try {
            String[] wordPartition = WordsSplit.separateWords(input, " ", true);
            int contentIdx = 1;
            String content = WordsSplit.getWord(wordPartition, contentIdx);

            LocalDateTime searchDate = TaskInsertion.dateConversion(content);
            List<Task> output = new ArrayList<>();

            IterateList.searchList(taskList, searchDate, output);

            if (output.isEmpty()) {
                Ui.emptyResult(response);
            } else {
                Ui.appendResponse(response, output);
            }
        } catch (TaylorException err) {
            throw new TaylorException(err.getMessage());
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Description is empty. (Format: SEARCH <YYYY-MM-DD HHmm>");
        }
        return response.toString();
    }
}
