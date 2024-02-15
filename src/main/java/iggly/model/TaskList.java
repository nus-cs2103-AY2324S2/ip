package iggly.model;

import java.util.ArrayList;

/**
 * The {@link TaskList} class encapsulates a task list and provides methods to
 * manipulate and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs an empty {@link TaskList} with an initial capacity of 100.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Constructs a {@link TaskList} with the specified list of tasks loaded from storage.
     *
     * @param taskList The list of tasks to be initialized with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the task at the specified index in the list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param task The task to be removed.
     */
    public void remove(Task task) {
        this.taskList.remove(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the filtered task list based on user's input.
     *
     * @param input user's desired find task keyword.
     * @return the filtered task list.
     */
    public TaskList find(String input) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        String lowerInput = input.toLowerCase();

        for (Task task : taskList) {
            if (task.getTitle().toLowerCase().contains(lowerInput)) {
                filteredTaskList.add(task);
            }
        }

        return new TaskList(filteredTaskList);
    }
}
