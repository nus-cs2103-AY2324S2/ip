package taskList;

import excceptions.WeiException;
import tasks.Task;

import java.util.ArrayList;

/**
 * Represents a collection of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Indicates the number of tasks in the TaskList
     *
     * @return number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Works as a getter to get a task.
     *
     * @param order order of the task in the TaskList.
     * @return the task requested.
     */
    public Task getTask(int order) {
        return tasks.get(order);
    }

    /**
     * Lists out the tasks in the TaskList.
     *
     * @return a text version of the tasks.
     */
    public String list() {
        String wholeList = "";
        for (int i = 0; i < tasks.size(); i++) {
            int order = i + 1;
            Task task = tasks.get(i);
            String text = task.stringify();
            wholeList += order + ". " + text + "\n";
        }
        return wholeList;
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param task The task to be added.
     * @return text version of the task.
     */
    public String add(Task task) {
        tasks.add(task);
        return task.stringify();
    }

    /**
     * Marks the task as complete.
     *
     * @param order order of the task in the TaskList.
     * @return text version of the task.
     */
    public String mark(int order) {
        Task task = tasks.get(order);
        task.setStatus(true);
        return task.stringify();
    }

    /**
     * Marks the task as incomplete.
     *
     * @param order order of the task in the TaskList.
     * @return text version of the task.
     */
    public String unmark(int order) {
        Task task = tasks.get(order);
        task.setStatus(false);
        return task.stringify();
    }

    /**
     * Deletes the task specified.
     *
     * @param order order of the task in the TaskList.
     * @return text version of the task.
     */
    public String delete(int order) {
        Task task = tasks.get(order);
        tasks.remove(order - 1);
        return task.stringify();
    }

}
