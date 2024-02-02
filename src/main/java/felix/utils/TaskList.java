package felix.utils;

import felix.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds task from taskList.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Returns task at index i from taskList.
     */
    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    /**
     * Returns number of tasks in taskList.
     */
    public int getCount() {
        return this.taskList.size();
    }

    /**
     * Deletes task at index i from taskList.
     */
    public void deleteTask(int i) {
        this.taskList.remove(i);
    }

    /**
     * Returns the String representation of all tasks within taskList to be written to a file.
     */
    public String getFileRepresentation() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            output.append(this.taskList.get(i).toFileString());
            if (i < this.taskList.size() - 1) output.append("\n");
        }
        return output.toString();
    }

    /**
     * Returns String representation of all tasks within taskList to be displayed.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            output.append(String.format("%d. %s", i + 1, this.taskList.get(i)));
            if (i < this.taskList.size() - 1) output.append("\n");
        }
        return output.toString();
    }
}
