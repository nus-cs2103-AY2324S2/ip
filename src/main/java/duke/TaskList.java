package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks
 */
public class TaskList {

    /**
     * List of tasks
     */
    protected ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList with existing ArrayList of tasks
     * @param newTasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> newTasks) {
        this.tasks = newTasks;
    }

    /**
     * Adds a task to the list of tasks
     * @param newTask Task to be added
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Deletes a task from the list of tasks
     * @param index Index of the task to be deleted
     * @return Task that was deleted
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks a task of the given index as done
     * @param index Index of the task to be marked as done
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks a task of the given index as undone
     * @param index Index of the task to be marked as undone
     */
    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

    /**
     * Returns the task of the given index
     * @param index Index of the task to be returned
     * @return Task of the given index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list
     * @return Number of tasks in the list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks to be printed to the user
     * @return List of tasks to be printed to the user
     */
    public String[] getTaskStrings() {
        String[] taskStrings = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            taskStrings[i] = this.tasks.get(i).toString();
        }
        return taskStrings;
    }

    /**
     * Returns the list of tasks to be saved to file
     * @return List of tasks to be saved to file
     */
    public String[] getFileStrings() {
        String[] storageStrings = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            storageStrings[i] = this.tasks.get(i).toFileString();
        }
        return storageStrings;
    }
}
