package ping;
import java.util.ArrayList;

import ping.job.Task;


/**
 * This class is used to create a list of tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public int taskSize() {
        return tasks.size();
    }

    public Task deleteTask(int idx) {
        return tasks.remove(idx);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public ArrayList<Task> allTasks() {
        return tasks;
    }

    /**
     * This method is used to detect Duplicate tasks
     *
     * @param task the task to be checked
     * @return true if the task is a duplicate, false otherwise
     */
    public boolean detectDuplicate(Task task) {
        for (Task t : tasks) {
            if (t.getDescription().equals(task.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to find tasks that contain the keyword
     *
     * @param keyword the keyword to search for
     * @return an ArrayList of tasks that contain the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().contains(keyword)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}

