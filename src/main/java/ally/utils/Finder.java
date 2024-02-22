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
        boolean found = false;
        StringBuilder sb = new StringBuilder();
        int ctr = 1;
        TaskList resultList = new TaskList();
        for (Task t : this.lst) {
            if (t.toString().contains(s)) {
                sb.append(ctr + ": " + t.toString());
                found = true;
            }
            ctr++;
        }
        if (!found) {
            sb.append("No results found.");
        } else {
            sb.append("\nAll results shown.");
        }
        return sb.toString();
    }
}
