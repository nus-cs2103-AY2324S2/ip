package remi.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import remi.utils.RemiError;

/**
 * Stores the list of Tasks, allows you to add, remove, and find tasks by index.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Simply add a task to the TaskList object.
     *
     * @param t task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Mark a task, does nothing if task is already marked.
     * @param idx the 1-indexed value of the task
     */
    public void markTask(int idx) {
        getTask(idx).mark();
    }

    /**
     * Unmark a task, does nothing if task isn't marked yet.
     * @param idx the 1-indexed value of the task
     */
    public void unmarkTask(int idx) {
        getTask(idx).unmark();
    }

    /**
     * Gets the task based on its idx in the TaskList
     *
     * @param idx the 1-indexed value of the task to be removed
     * @return the task object
     */
    public Task getTask(int idx) {
        return taskList.get(idx - 1);
    }

    /**
     * Gets a list of tasks that contain the queryString in their label.
     *
     * @param queryString the string to be queried in all the task labels
     * @return the list of tasks
     */
    public List<Task> getTask(String queryString) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t: taskList) {
            if (t.checkQuery(queryString)) {
                res.add(t);
            }
        }
        return res;
    }

    /**
     * Creates a copy of this taskList that is sorted alphabetically based on label.
     *
     * @return a copy of the TaskList object that is sorted
     */
    public TaskList getSortedTasks() {
        ArrayList<Task> sorted = new ArrayList<Task>(taskList);
        sorted.sort(Comparator.comparing(Task::getLabel));
        return new TaskList(sorted);
    }

    /**
     * Removes a task from the taskList based on idx.
     *
     * @param idx the 1-indexed value of the task to be removed
     * @throws RemiError when invalid idx is given
     */
    public void removeTask(int idx) throws RemiError{
        if (idx < 0 || taskList.size() <= idx) {
            throw new RemiError("Task is out of bounds and couldn't be removed.");
        }
        taskList.remove(idx - 1);
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Returns the numbered list of tasks.
     *
     * @return list of tasks as a string
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        // NOTE: May be slow
        for (int i = 1; taskList.size() >= i; i++) {
            res.append(String.format("%d. %s", i, getTask(i)));
            if (i != taskList.size()) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    /**
     * Returns a parsable string of the deadline task. Meant to be used for remi.storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    public String getParsableString() {
        StringBuilder res = new StringBuilder();
        // NOTE: May be slow
        for (int i = 1; taskList.size() >= i; i++) {
            res.append(getTask(i).getParsableString());
            if (i != taskList.size()) {
                res.append("\n");
            }
        }
        return res.toString();
    }
}
