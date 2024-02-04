package Jerry;

import java.util.ArrayList;

/**
 * Represents a collection of tasks in the chatbot application. Provides methods to manipulate tasks, such as adding, deleting, and listing tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {

        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public String printTask(int index) {
        return tasks.get(index).toString();
    }

    public void mark(int index) {
        tasks.get(index).markDone();
    }

    public void unmark(int index) {
        tasks.get(index).markNotDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
