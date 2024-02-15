package tommy.task;

import java.util.ArrayList;

/**
 * Represents an ArrayList of objects of type Tasks.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Constructor for an empty ArrayList to store tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor for an ArrayList filled with a list of tasks retrieved
     * from storage.
     *
     * @param taskList An ArrayList of task retrieved from the storage
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the input task to the taskList.
     *
     * @param task Task to be added to the taskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the size of the taskList.
     *
     * @return Size of taskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the taskList as ArrayList type.
     *
     * @return TaskList as an ArrayList.
     */
    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    /**
     * Returns the task in the taskList at the position specified.
     *
     * @param position Position of task in the taskList to be retrieved.
     * @return Task at specified position in the taskList.
     */
    public Task getTaskAtPosition(int position) {
        return this.taskList.get(position - 1);
    }

    /**
     * Marks the task in the specified position in the taskList as done.
     *
     * @param position Position of task in the taskList to be marked as done.
     */
    public void markTask(int position) {
        Task task = taskList.get(position - 1);
        task.setDone(true);
    }

    /**
     * Marks the task in the specified position in the taskList as not done.
     *
     * @param position Position of task in the taskList to be marked as not done.
     */
    public void unmarkTask(int position) {
        Task task = taskList.get(position - 1);
        task.setDone(false);
    }

    /**
     * Deletes the task in the specified position in the taskList.
     *
     * @param position Position of task in the taskList to be deleted.
     */
    public void deleteTaskAtPosition(int position) {
        taskList.remove(position - 1);
    }
}
