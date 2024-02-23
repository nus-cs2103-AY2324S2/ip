package duke.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import duke.tasks.Task;

/**
 * The TaskList class represents a list of tasks.
 * It provides methods to manipulate the list, such as adding, deleting, marking, and unmarking tasks,
 * as well as searching for tasks with specific criteria.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        sortTaskList();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @return The task that was marked as done.
     */
    public Task markTask(int index) {
        return tasks.get(index).markDone();
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param index The index of the task to mark as undone.
     * @return The task that was marked as undone.
     */
    public Task unMarkTask(int index) {
        return tasks.get(index).unMarkDone();
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param t The task to add.
     * @return The task that was added.
     */
    public Task addTask(Task t) {
        tasks.add(t);
        sortTaskList();
        return t;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds tasks with the specified date and returns them as an ArrayList.
     *
     * @param dateToFind The date to search for.
     * @return An ArrayList containing tasks with the specified date.
     */
    public ArrayList<Task> findTaskWithDate(LocalDateTime dateToFind) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            boolean taskHasDate = currentTask.hasDate(dateToFind);
            if (taskHasDate) {
                result.add(currentTask);
            }
        }
        return result;
    }

    /**
     * Finds tasks containing the specified string in their description and returns them as an ArrayList.
     *
     * @param stringToFind The string to search for in task descriptions.
     * @return An ArrayList containing tasks with descriptions containing the specified string.
     */
    public ArrayList<Task> findTasksWithString(String stringToFind) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            boolean taskDescriptionHasWord = currentTask.descriptionHasWord(stringToFind);
            if (taskDescriptionHasWord) {
                result.add(currentTask);
            }
        }
        return result;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }
    /**
     * Sorts the task list using a custom comparator.
     * The sorting is done based on the natural ordering of the Task objects.
     */
    private void sortTaskList() {
        // Define a custom comparator for Task objects
        Comparator<Task> taskComparator = new Comparator<>() {
            /**
             * Compares two tasks based on their natural ordering.
             * @param task1 The first task to compare.
             * @param task2 The second task to compare.
             * @return An integer value:
             *         - 0 if task1 is equal to task2,
             *         - a negative value if task1 is less than task2,
             *         - a positive value if task1 is greater than task2.
             */
            @Override
            public int compare(Task task1, Task task2) {
                return task1.compareTo(task2); // Delegates comparison to the Task class' compareTo method
            }
        };
        // Sort the tasks using the defined comparator
        Collections.sort(this.tasks, taskComparator);
    }

    /**
     * clear the current taskList
     */
    public void clearTaskList() {
        this.tasks = new ArrayList<>();
    }
}
