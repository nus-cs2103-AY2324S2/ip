package talkingbot.util;

import java.util.ArrayList;

import talkingbot.task.Task;

/**
 * Class representing the list of tasks.
 */
public class TaskList {
    private static final String FILTER_LIST_MSG = "\tHere are the tasks with descriptions matching your query:\n";
    private static final String DISPLAY_LIST_MSG = "\tHere are the tasks in your list:\n";
    private static final String LIST_ITEM_FORMAT = "\t%d. %s";
    private final ArrayList<Task> tasks;

    /**
     * Creates a new TaskList instance.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Creates a new TaskList instance with a predefined list of tasks.
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
     * Returns a filtered list of tasks that contains filterString
     * in their descriptions.
     *
     * @param filterString String to filter task descriptions with.
     * @return String of filtered tasks.
     */
    public String filterList(String filterString) {
        StringBuilder returnedString = new StringBuilder();
        returnedString.append(FILTER_LIST_MSG);

        for (int idx = 0; idx < this.tasks.size(); idx++) {
            Task curTask = this.getTask(idx);

            if (curTask.getDescription().contains(filterString)) {
                returnedString.append(String.format(LIST_ITEM_FORMAT, idx + 1, this.getTask(idx).toString()));

                returnedString.append("\n");
            }
        }
        returnedString.deleteCharAt(returnedString.length() - 1);
        return returnedString.toString();
    }

    /**
     * Returns how the TaskList object will be printed as a String.
     *
     * @return String of tasks.
     */
    @Override
    public String toString() {
        StringBuilder returnedString = new StringBuilder();
        returnedString.append(DISPLAY_LIST_MSG);

        for (int idx = 0; idx < this.tasks.size(); idx++) {
            returnedString.append(String.format(LIST_ITEM_FORMAT, idx + 1, this.getTask(idx).toString()));

            if (idx != this.getSize() - 1) {
                returnedString.append("\n");
            }
        }
        return returnedString.toString();
    }
}
