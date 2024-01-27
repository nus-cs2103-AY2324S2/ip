package talkingbot.util;

import java.util.ArrayList;

import talkingbot.task.Task;

/**
 * Class representing the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Constructor for the TaskList class with a predefined list of tasks.
     *
     * @param tasks ArrayList of Tasks to be put initially.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the ArrayList of Tasks.
     *
     * @param newTask Task to be added.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Removes the task from the ArrayList of Tasks.
     *
     * @param idx Index of task to be removed.
     * @return The removed task.
     */
    public Task removeTask(int idx) {
        return this.tasks.remove(idx);
    }

    /**
     * Returns the task at a certain index.
     *
     * @param idx Index of task to be retrieved.
     * @return The desired task.
     */
    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Sets the task at a particular index.
     *
     * @param idx Index in the ArrayList to be set.
     * @param task Task to be set at the index.
     */
    public void setTask(int idx, Task task) {
        this.tasks.set(idx, task);
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns how the TaskList object will be printed as a String.
     *
     * @return A formatted String.
     */
    public String filterList(String filterString) {
        StringBuilder returnedString = new StringBuilder();
        returnedString.append("\tHere are the tasks with descriptions matching your query:\n");

        for (int idx = 0; idx < this.tasks.size(); idx++) {
            Task curTask = this.getTask(idx);

            if (curTask.getDescription().contains(filterString)) {
                returnedString.append(String.format("\t%d. %s", idx + 1, this.getTask(idx).toString()));

                if (idx != this.getSize() - 1) {
                    returnedString.append("\n");
                }
            }
        }
        return returnedString.toString();
    }

    @Override
    public String toString() {
        StringBuilder returnedString = new StringBuilder();
        returnedString.append("\tHere are the tasks in your list:\n");

        for (int idx = 0; idx < this.tasks.size(); idx++) {
            returnedString.append(String.format("\t%d. %s", idx + 1, this.getTask(idx).toString()));

            if (idx != this.getSize() - 1) {
                returnedString.append("\n");
            }
        }
        return returnedString.toString();
    }
}
