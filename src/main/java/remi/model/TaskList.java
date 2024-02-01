package remi.model;

import remi.utils.RemiError;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Simply add a task to the TaskList object.
     *
     * @param t task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    public void markTask(int idx) {
        getTask(idx).mark();
    }

    public void unmarkTask(int idx) {
        getTask(idx).unmark();
    }

    public Task getTask(int idx) {
        return taskList.get(idx - 1);
    }

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
    public String parsableString() {
        StringBuilder res = new StringBuilder();
        // NOTE: May be slow
        for (int i = 1; taskList.size() >= i; i++) {
            res.append(getTask(i).parsableString());
            if (i != taskList.size()) {
                res.append("\n");
            }
        }
        return res.toString();
    }
}
