package duke;
import java.util.ArrayList;

/**
 * Simple arraylist stores tasks
 * @author Cedric
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    /**
     * Clears the tasklist
     */
    public void clear() {
        taskList.clear();
    }
    /**
     * Adds a task to the list
     * @param t the task to be added
     */
    public void add(Task t) {
        taskList.add(t);
    }
    /**
     * Deletes a task from the list
     * @param numberToDelete the task to delete based on sequence
     * @returns task that was deleted
     */
    public Task delete(int numberToDelete) {
        if (numberToDelete < taskList.size() && numberToDelete >= 0) {
            Task t = taskList.remove(numberToDelete);
            return t;
        }
        return null;
    }
    /**
     * Gets a task from the list
     * @param numberToGet the task to be returned based on sequence
     * @returns the task that was found
     */
    public Task get(int numberToGet) {
        if (numberToGet < taskList.size() && numberToGet >= 0) {
            return taskList.get(numberToGet);
        }
        return null;
    }
    /**
     * returns length of list
     * @returns length of list
     */
    public int getLength() {
        return taskList.size();
    }
    /**
     * returns an arraylist of all tasks found that contains the input String L
     * @param L the string to find in the tasks
     * @return the arraylist containing the tasks found
     */
    public ArrayList<Task> find(String L) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String action = t.toString();
            String export = t.export();
            if (action.contains(L) || export.contains(L)) {
                tasks.add(t);
            }
        }
        return tasks;
    }

    /**
     * returns all tasks in the list
     * @return string representing all tasks in list
     */
    public String getList() {
        if (taskList.size() == 0) {
            return "You have no tasks in your list!";
        } else {
            String result = "Here are your tasks in your list:\n";
            for (int x = 0; x < taskList.size(); x++) {
                Task item = taskList.get(x);
                int numeric = x + 1;
                result = result + numeric + "." + item.toString() + "\n";
            }
            return result;
        }
    }
}
