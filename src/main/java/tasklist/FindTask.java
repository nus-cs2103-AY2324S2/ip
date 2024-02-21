package tasklist;

import java.util.List;

import exceptions.TaylorException;
import taskhelper.IterateList;
import taskhelper.WordsSplit;
import tasks.Task;
import ui.Ui;


/**
 * Finds a task by searching for a keyword.
 */
public class FindTask {
    private static final int KEYWORD = 1;
    /**
     * No constructor needed.
     */
    private FindTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Executes finder using keyword.
     *
     * @param input User input
     * @param taskList ArrayList of Tasks
     */
    public static String exec(String input, List<List<Task>> taskList) {
        StringBuilder response = new StringBuilder();
        String[] wordPartition = WordsSplit.separateWords(input, " ", true);
        boolean isKeywordEmpty = WordsSplit.getWord(wordPartition, KEYWORD).trim().isBlank();
        if (isKeywordEmpty) {
            throw new TaylorException("\n"
                    + "For in the void, there's room to grow,\n"
                    + "And from the ashes, new beginnings will sow\n"
                    + "==============================\n"
                    + "The description of the task is empty.");
        }

        String keyword = WordsSplit.getWord(wordPartition, KEYWORD);
        List<Task> result = IterateList.findContains(taskList, keyword);

        if (result.isEmpty()) {
            Ui.emptyResult(response);
        } else {
            Ui.appendResponse(response, result);
        }
        return response.toString();
    }
}
