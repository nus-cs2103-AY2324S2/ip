package felix.utils;

import felix.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds task from taskList.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Returns task at index i from taskList.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Returns number of tasks in taskList.
     */
    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Deletes task at index i from taskList.
     */
    public void deleteTask(int i) {
        this.tasks.remove(i);
    }

    /**
     * Returns the String representation of all tasks within taskList to be written to a file.
     */
    public String getFileRepresentation() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(this.tasks.get(i).toFileString());
            if (i < this.tasks.size() - 1) output.append("\n");
        }
        return output.toString();
    }

    /**
     * Returns String representation of all tasks within taskList to be displayed.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append(String.format("%d. %s", i + 1, this.tasks.get(i)));
            if (i < this.tasks.size() - 1) output.append("\n");
        }
        return output.toString();
    }
}
