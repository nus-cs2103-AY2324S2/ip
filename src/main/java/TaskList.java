import task.Task;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks.
 * It provides methods for adding, removing, and accessing tasks.
 */
public class TaskList {
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private int lastIdx = 0;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        this.lastIdx = taskArrayList.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.taskArrayList.add(task);
        this.lastIdx++;
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param i The index of the task to remove.
     */
    public void removeTask(int i) {
        this.taskArrayList.remove(i);
        this.lastIdx--;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param idx The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTaskByIdx(int idx) {
        return this.taskArrayList.get(idx);
    }

    /**
     * Retrieves the index of the last task in the list.
     *
     * @return The index of the last task.
     */
    public int getLastIdx() {
        return this.lastIdx;
    }

}
