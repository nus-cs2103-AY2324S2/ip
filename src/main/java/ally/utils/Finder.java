package ally.utils;

import ally.task.Task;

/**
 * Searches for task based on Search Keyword.
 */
public class Finder {
    private TaskList lst;
    /**
     * Constructor for Finder object
     * @param lst list of Tasks currently
     */
    public Finder(TaskList lst) {
        this.lst = lst;
    }

    /**
     * Finds specified String in current list of tasks
     * @param s String to be found
     * @return String response from Duke
     */
    public String find(String s) {
        boolean isFound = false;
        StringBuilder sb = new StringBuilder();
        int ctr = 1;
        TaskList resultList = new TaskList();
        for (Task t : this.lst) {
            if (t.toString().contains(s)) {
                sb.append(ctr + ": " + t);
                isFound = true;
            }
            ctr++;
        }
        if (!isFound) {
            sb.append("No results Found.");
        } else {
            sb.append("\nAll results shown.");
        }
        return sb.toString();
    }
}
