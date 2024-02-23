package lindi.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the taskList to be used in the application.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the number of tasks in the lindi.task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at index from task list, and returns it
     *
     * @param listIndex index of task to delete as shown by list command
     * @return the task that was deleted
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Task delete(int listIndex) throws IndexOutOfBoundsException {
        return tasks.remove(listIndex - 1);
    }

    /**
     * Marks the item in task list at index as done, and returns it
     *
     * @param listIndex index of task to mark as shown by list command
     * @return the task that was marked
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Task mark(int listIndex) throws IndexOutOfBoundsException {
        Task task = tasks.get(listIndex - 1);
        task.markDone();
        return task;
    }

    /**
     * Unmarks the item in task list at index as done, and returns it
     *
     * @param listIndex index of task to unmark as shown by list command
     * @return the task that was unmarked
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public Task unmark(int listIndex) throws IndexOutOfBoundsException {
        Task task = tasks.get(listIndex - 1);
        task.unmarkDone();
        return task;
    }

    /**
     * Returns the tasks as an Iterator to prevent modification.
     */
    public Iterator<Task> getTasks() {
        return this.tasks.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s \n", i + 1, tasks.get(i).toString()));
        }
        if (tasks.size() == 0) {
            sb.append("There are no tasks for you today. Enjoy :)");
        }
        return sb.toString();
    }
}
