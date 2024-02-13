package duke.tasks;

import java.util.ArrayList;

/**
 * Represents a list of duke tasks.
 */
public class TaskList {

    /**
     * The list of duke tasks.
     */
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Constructor for the list of duke tasks.
     */
    public TaskList() {
    }

    /**
     * Adds a task to the list of duke tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the list of duke tasks.
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        list.remove(index - 1);
    }

    /**
     * Returns the size of the list of duke tasks.
     * @return The size of the list of duke tasks.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the task at index int.
     * @param index The index of the task to be returned.
     * @return the task at index int.
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Returns the list of duke tasks.
     * @param search The search string to find tasks.
     * @return The list of duke tasks.
     */
    public TaskList findTasks(String search) {
        TaskList found = new TaskList();
        for (Task t : list) {
            if (t.getDescription().contains(search)) {
                found.addTask(t);
            }
        }
        return found;
    }
}
