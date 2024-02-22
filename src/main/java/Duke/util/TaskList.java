package duke.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
}
