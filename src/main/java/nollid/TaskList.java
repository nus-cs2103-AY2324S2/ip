package nollid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import nollid.tasks.Task;

/**
 * TaskList class represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    /**
     * The internal ArrayList to store tasks.
     */
    private final ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified ArrayList of tasks.
     *
     * @param taskList The ArrayList of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Adds a variable number of tasks to the TaskList.
     *
     * @param tasksToAdd The tasks to be added.
     */
    public void add(Task... tasksToAdd) {
        this.taskList.addAll(Arrays.asList(tasksToAdd));
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Gets the task at the specified index in the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Removes and returns the task at the specified index in the TaskList.
     *
     * @param index The index of the task to remove.
     * @return The task removed from the TaskList.
     */
    public Task remove(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Marks the task with the given index as done or not done.
     *
     * @param taskIndex The index of the task to mark.
     * @param isDone    true if the task is done, false if not done.
     */
    public void setDone(int taskIndex, boolean isDone) {
        this.taskList.get(taskIndex - 1).setDone(isDone);
    }

    /**
     * Provides an iterator for the TaskList.
     *
     * @return An iterator for the TaskList.
     */
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    /**
     * Provides a summary of the number of tasks in the TaskList.
     *
     * @return A summary string indicating the number of tasks in the TaskList.
     */
    public String summary() {
        int listSize = this.taskList.size();

        // "task" for singular, "tasks" for plural
        if (listSize == 1) {
            return "You now have " + listSize + " task in your list.";
        } else {
            return "You now have " + listSize + " tasks in your list.";
        }
    }

    /**
     * Generates a success message for adding a new task.
     *
     * @param t The task that was added.
     * @return The formatted success message.
     */
    public String getAddSuccessMessage(Task t) {
        String message = "Alright, added:\n" + "\t" + t + "\n";
        message += this.summary();

        return message;
    }
}
