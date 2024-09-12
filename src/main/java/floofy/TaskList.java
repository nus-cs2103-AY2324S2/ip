package floofy;

import floofy.task.Task;

import java.util.ArrayList;

/**
 * Represents the list of tasks tracked.
 */
public class TaskList {

    /** The data structure used to store the list of tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the given ArrayList of tasks.
     *
     * @param tasks The ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the task at the given index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns a String array representing the list of tasks to be saved to the storage file.
     *
     * @return The list of tasks as a String array, where each task is represented in its file format.
     */
    public String[] getTaskList() {
        String[] taskList = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            taskList[i] = this.tasks.get(i).toFileFormat();
        }
        return taskList;
    }

    /**
     * Returns the number of tasks in the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Deletes a task from the TaskList given the index.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks a task as done given the index.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markTask();
    }

    /**
     * Marks a task as undone given the index.
     */
    public void markTaskAsUndone(int index) {
        this.tasks.get(index).unmarkTask();
    }

    /**
     * Finds the tasks that match the given keyword.
     *
     * @return The TaskList containing the matching tasks.
     */
    public TaskList findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.hasMatchingDescription(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }
}
