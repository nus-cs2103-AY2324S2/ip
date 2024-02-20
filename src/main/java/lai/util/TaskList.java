package lai.util;

import lai.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of tasks in the Lai application.
 * This class provides functionality to add, remove, and access tasks,
 * as well as to iterate over all tasks.
 */
public class TaskList implements Iterable<Task> {
    protected List<Task> tasks = new ArrayList<>();

    /**
     * Constructs a TaskList with a specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list and updates the storage file.
     * Also prints a message indicating the task has been added.
     *
     * @param newTask The task to be added.
     * @param storage The storage utility to update the tasks file.
     * @return The updated list of tasks.
     */
    public TaskList add(Task newTask, Storage storage) {
        this.tasks.add(newTask);
        storage.updateTasksFile(this);

        return this;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task by its position in the list.
     *
     * @param index The index of the task in the list.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes a specified task from the list.
     *
     * @param t The task to be removed.
     * @return {@code true} if the task was successfully removed; {@code false} otherwise.
     */
    public boolean remove(Task t) {
        return this.tasks.remove(t);
    }

    /**
     * Searches for tasks that contain the specified query in their description.
     * This method iterates over all tasks in the list and checks if the task's description contains the query string.
     * If a match is found, the task is added to the result list.
     *
     * @param query The string to search for within task descriptions.
     * @return A list of {@code Task} objects whose descriptions contain the specified query.
     */
    public List<Task> find(String query) {
        List<Task> result  = new ArrayList<>();
        for (Task task : this.tasks) {
            int index = task.getDescription().indexOf(query);
            if (index != -1) {
                result.add(task);
            }
        }

        return result;
    }

    /**
     * Returns an iterator over the tasks in the list.
     *
     * @return An Iterator of tasks in the list.
     */
    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}
