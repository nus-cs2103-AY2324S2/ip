package chatbot.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private int numTasks;
    private boolean isSaved;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
        this.isSaved = false;
    }

    /**
     * adds a task to the list.
     * @param task The task to be added.
     */
    public boolean addTask(Task task) {
        this.tasks.add(task);
        this.numTasks++;
        this.isSaved = false;
        return true;
    }

    /**
     * Deletes a task from the list.
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task task = this.tasks.remove(index);
        this.numTasks--;
        this.isSaved = false;
        return task;
    }

    /**
     * Gets the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : this.tasks) {
            list.add(task);
        }
        return list;
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked.
     * @return The marked task.
     */
    public Task markTask(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        this.isSaved = false;
        return task;
    }

    /**
     * Unmarks a task.
     * @param index The index of the task to be unmarked.
     * @return The unmarked task.
     */
    public Task unmarkTask(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        this.isSaved = false;
        return task;
    }

    /**
     * Gets the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int getNumTasks() {
        return this.numTasks;
    }

    /**
     * Checks if the list of tasks is saved.
     * @return True if the list of tasks is saved, false otherwise.
     */
    public boolean isSaved() {
        return this.isSaved;
    }

    /**
     * Marks the list of tasks as saved.
     */
    public void save() {
        this.isSaved = true;
    }

    /**
     * Finds tasks that contain the keyword.
     * @param keyword The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                list.add(task);
            }
        }
        return list;
    }

}
