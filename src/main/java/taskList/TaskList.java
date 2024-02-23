package taskList;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.WeiException;
import tasks.Task;

/**
 * Represents a collection of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
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
            String nextTask = order + ". " + text + "\n";
            wholeList += nextTask;
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
     * @throws WeiException if order of task is incorrect.
     */
    public String mark(int order) throws WeiException {
        if (order >= tasks.size()) {
            throw new WeiException("task doesn't exist");
        }
        Task task = tasks.get(order);
        task.setStatus(true);
        return task.stringify();
    }

    /**
     * Marks the task as incomplete.
     *
     * @param order order of the task in the TaskList.
     * @return text version of the task.
     * @throws WeiException if order of task is incorrect.
     */
    public String unmark(int order) throws WeiException {
        if (order >= tasks.size()) {
            throw new WeiException("task doesn't exist");
        }
        Task task = tasks.get(order);
        task.setStatus(false);
        return task.stringify();
    }

    /**
     * Deletes the task specified.
     *
     * @param order order of the task in the TaskList.
     * @return text version of the task.
     * @throws WeiException if order of task is incorrect.
     */
    public String delete(int order) throws WeiException {
        if (order >= tasks.size()) {
            throw new WeiException("task doesn't exist");
        }
        Task task = tasks.get(order);
        tasks.remove(order - 1);
        return task.stringify();
    }

    /**
     * Looks for tasks that matches the keyword.
     *
     * @param keyword Keyword.
     * @return List of tasks that matches the keyword.
     */
    public String find(String keyword) {
        String result = "";
        int order = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.search(keyword)) {
                String text = task.stringify();
                String nextTask = order + ". " + text + "\n";
                result += nextTask;
                order++;
            }
        }
        return result;
    }

    public void setReminder(int order, String date) {
        Task task = tasks.get(order);
        task.setReminder(date);
    }

    public String getRemindList() {
        String result = "";
        int order = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.checkIfRemind()) {
                String text = task.stringify();
                String nextTask = order + ". " + text + "\n";
                result += nextTask;
                order++;
            }
        }
        return result;
    }
}
