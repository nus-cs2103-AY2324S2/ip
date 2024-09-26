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
 * Searches for tasks based on Date and Time.
 */
public class SearchTask {
    /**
     * No constructor needed.
     */
    private SearchTask() {
        throw new AssertionError("Constructor is not allowed");
    }
    /**
     * Searches Task in List based on Date and Time.
     *
     * @param input Date and Time to search
     * @param taskList ArrayList with Tasks
     */
    public static StringBuilder execSearchTask(String input, List<List<Task>> taskList) throws TaylorException {
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
            throw new TaylorException("\n"
                    + "In the stillness of this empty room,\n"
                    + "I'm left to wander in the gloom\n"
                    + "==============================\n"
                    + "Description is empty. (Format: SEARCH <YYYY-MM-DD HHmm>");
        }
        return response;
    }
}
