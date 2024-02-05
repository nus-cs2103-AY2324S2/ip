package duke;

import duke.task.Task;

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
     * @return boolean found flag
     */
    void find(String s) {
        boolean found  = false;
        int ctr = 1;
        TaskList resultList = new TaskList();
        for (Task t : this.lst) {
            if (t.toString().contains(s)) {
                System.out.println(ctr + ": " + t.toString());
                found = true;
            }
            ctr++;
        }
        if (!found) {
            System.out.println("No results found.");
        } else {
            System.out.println("All results shown.");
        }
    }

}
