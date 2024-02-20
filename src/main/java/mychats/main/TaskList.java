package mychats.main;
import mychats.exception.MyChatsException;
import mychats.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks to create the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task get(int zeroItem) {
        Task task = tasks.get(zeroItem);
        assert task != null : "Task at index " + zeroItem + " should not be null";
        return tasks.get(zeroItem);
    }

    public int getSize() {
        int taskSize = tasks.size();
        assert taskSize >= 0 : "Size of task list should be greater than or equal to 0";
        return taskSize;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
    }

    /**
     * Marks a task as done based on the given zero-indexed task number.
     *
     * @param num The zero-indexed task index to mark as done.
     */
    public void markAsDone(int num) {
        Task taskToMark = tasks.get(num);
        assert taskToMark != null : "Task to mark as done cannot be null";
        taskToMark.markAsDone();
    }

    /**
     * Marks a task as not done based on the given zero-indexed task number.
     *
     * @param num The zero-indexed task index to mark as not done.
     */
     public void unMarkAsDone(int num) {
         Task taskToUnMark = tasks.get(num);
         assert taskToUnMark != null : "Task to mark as not done cannot be null";
         taskToUnMark.unMarkAsDone();
    }

    /**
     * Deletes a task from the TaskList based on the given zero-indexed task number.
     *
     * @param item The zero-indexed task number to be deleted.
     * @throws MyChatsException If asked to delete a task with one-indexed task number
     * of 0 or if one-indexed task number is greater than the size of the task list.
     */
     public void deleteTask(int item) throws MyChatsException {
        boolean isWithinLowerLimit = item >= 0;
        boolean isWithinUpperLimit = item < tasks.size();
        assert tasks.get(item) != null : "Task to delete cannot be null";
        if (isWithinLowerLimit && isWithinUpperLimit) {
            tasks.remove(item);
        } else {
            int oneItem = item + 1;
            throw new MyChatsException("Error! Task number '" + oneItem + "' does not exist.");
        }
    }

    /**
     * Removes the most recently added task from the TaskList.
     *
     * @throws MyChatsException If the task list is empty.
     */
    public void undoRecentTask() throws MyChatsException {
        if (tasks.size() == 0) {
            throw new MyChatsException("Error! There are no tasks in the list to undo.");
        } else {
            int lastIndex = tasks.size() - 1;
            tasks.remove(lastIndex);
        }
    }
}