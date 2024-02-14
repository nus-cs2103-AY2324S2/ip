package paimon.task;

import paimon.util.FileHandler;

import java.util.ArrayList;

/**
 * Manages a list of tasks in the application. This class provides functionality to add, delete,
 * and mark tasks as done or not done. It also handles saving the task list to a file after any modifications.
 */
public class TaskList {
    private final ArrayList<Task> list = new ArrayList<Task>();


    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {

    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Adds a new task to the task list and saves the updated list to a file.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        this.list.add(task);
        FileHandler.saveTaskList(this);
    }

    /**
     * Deletes a task from the task list by its index and saves the updated list to a file.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.list.remove(index);
        FileHandler.saveTaskList(this);
    }

    /**
     * Marks a task as done or not done based on the given index and the specified state,
     * then saves the updated task list to a file.
     *
     * @param index  The index of the task to be marked.
     * @param isDone The new completion state of the task.
     * @return true if the task's state was changed, false if it was already in the specified state.
     */
    public boolean markTask(int index, boolean isDone) {
        Task task = this.list.get(index);
        if (task.isDone == isDone) {
            return false;
        } else {
            task.setTaskState(isDone);
            FileHandler.saveTaskList(this);
            return true;
        }
    }

    /**
     * Returns a string representation of the task list, with each task numbered and formatted as a list item.
     *
     * @return A formatted string representing all tasks in the list.
     */
    public String toString() {
        int size = this.list.size();
        String returnString = "";
        if (size == 0) {
            return returnString;
        }
        for (int i = 0; i < size; i++) {
            String currentIndex = String.valueOf(i + 1);
            if (i == size - 1) {
                returnString = returnString + currentIndex + ": " + this.list.get(i).getTask();
            } else {
                returnString = returnString + currentIndex + ": " + this.list.get(i).getTask() + "\n";
            }
        }
        return returnString;
    }

    public String getFoundString(String keyword) {
        int size = this.list.size();
        String returnString = "";
        if (size == 0) {
            return returnString;
        }
        for (int i = 0; i < size; i++) {
            String currentIndex = String.valueOf(i + 1);
            Task currentTask = this.list.get(i);
            if (currentTask.containsKeyword(keyword)) {
                if (i == size - 1) {
                    returnString = returnString + currentIndex + ": " + this.list.get(i).getTask();
                } else {
                    returnString = returnString + currentIndex + ": " + this.list.get(i).getTask() + "\n";
                }
            }

        }
        return returnString;
    }
}
