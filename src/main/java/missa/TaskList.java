package missa;

import java.util.ArrayList;

import missa.task.Task;

/**
 * A class that contains the task list.
 */
public class TaskList {
    /** List of tasks added */
    private ArrayList<Task> taskList = null;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds new task into taskList.
     * @param t New task to be added.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Returns number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int getSize() {
        return taskList.size();
    }

    public Task getATask(int index) {
        return taskList.get(index);
    }

    /*
     * Returns items in task list.
     *
     * @return A string containing all tasks added by user.
     */
    public String getTasks() {
        String str = "";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            str += index + ". " + taskList.get(i) + "\n";
        }
        return str;
    }

    /**
     * Returns matching items in task list.
     *
     * @param keyword String used to search for tasks.
     * @return A string containing matching tasks added by user.
     */
    public String findTasks(String keyword) {
        String str = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.checkKeyword(keyword)) {
                str += (i + 1) + ". " + t + "\n";
            }
        }
        return str;
    }

    /**
     * Marks task as done.
     *
     * @param idx Index of task to be marked as done.
     */
    public void markTask(int idx) {
        Task t = taskList.get(idx);
        t.mark();
    }

    /**
     * Marks task as not done.
     *
     * @param idx Index of task to be marked as not done.
     */
    public void unmarkTask(int idx) {
        Task t = taskList.get(idx);
        t.unmark();
    }

    /**
     * Removes task.
     *
     * @param idx Index of task to be removed from the list.
     */
    public void deleteTask(int idx) {
        taskList.remove(idx);
    }

    /**
     * Gets string representations of all task data.
     *
     * @return String representations of data.
     */
    public String getUpdatedData() {
        String str = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (i == taskList.size() - 1) {
                str += t.getData();
            } else {
                str += t.getData() + "\n";
            }
        }
        return str;
    }
}
