package hal.search;

import java.util.ArrayList;
import java.util.regex.Pattern;

import hal.tasks.Task;


/**
 * The `Search` class provides a utility for searching tasks in a list based on a query.
 */
public class Search {
    /**
     * Searches for tasks in the given list that match the specified query.
     *
     * @param list  The list of tasks to search within.
     * @param query The search query to match against task names.
     * @return An ArrayList of tasks that match the query.
     */
    public static ArrayList<Task> search(ArrayList<Task> list, String query) {
        ArrayList<Task> resultList = new ArrayList<>();
        Pattern pattern = Pattern.compile(".*\\b" + query.toLowerCase() + "\\b.*");
        for (Task task : list) {
            boolean isMatch = pattern.matcher(task.getTaskName().toLowerCase()).matches();
            if (isMatch) {
                resultList.add(task);
            }
        }
        return resultList;
    }
}
