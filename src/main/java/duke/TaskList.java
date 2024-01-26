package duke;

import java.util.ArrayList;

/**
 * Represents a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Lists all the tasks in the task list in their string representations.
     * 
     * @return The list of tasks in their string representations.
     */
    public ArrayList<String> listTasks() {
        ArrayList<String> messages = new ArrayList<String>();
        for (int i = 0; i < this.tasks.size(); i++) {
            messages.add(String.format(
                    "%d.%s", i + 1, this.tasks.get(i).toString()));
        }
        return messages;
    }

    /**
     * Returns the task at the given index.
     * 
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     * 
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }
}
