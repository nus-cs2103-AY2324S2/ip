package duke;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks.
 * It provides functionalities to add, delete, retrieve, and search for tasks within the list.
 */
public class TaskList {
    private final ArrayList<Task> lst;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param lst The ArrayList of Task objects to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The Task to be added to the list.
     */
    public void addTask(Task newTask) {
        this.lst.add(newTask);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param num The index of the task to be deleted.
     */
    public void deleteTask(int num) {
        this.lst.remove(num);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getLst() {
        return this.lst;
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param num The index of the task to retrieve.
     * @return The Task at the specified index.
     */
    public Task get(int num) {
        return this.lst.get(num);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.lst.size();
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword.
     *
     * @param keyword The string to search for in the task descriptions.
     * @return An ArrayList of Tasks that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : this.lst) {
            if (t.toString().contains(keyword)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}
