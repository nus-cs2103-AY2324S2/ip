package podz.task;
import java.util.ArrayList;
import java.util.stream.Collectors;

import podz.storage.Storage;

/**
 * Represents a list of tasks in the application.
 */
public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a <code>TaskList</code> object with the specified list of tasks.
     * 
     * @param tasks the list of tasks to initialise with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * Adds a task to the task list.
     * 
     * @param t the task to add.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        Storage.updateSaved(tasks);
    }

    /**
     * Deletes a task from the task list based on index.
     * 
     * @param i the index of the task to delete.
     */
    public void deleteTask(int i) {
        assert i >= 0;
        this.tasks.remove(i);
        Storage.updateSaved(tasks);
    }

    /**
     * Retrieves a task from the task list based on index.
     * 
     * @param i the index of the task to retrieve.
     * @return the task at the specified index.
     */
    public Task getTask(int i) {
        assert i >= 0;
        return this.tasks.get(i);
    }

    /**
     * Gets the number of tasks in the task list.
     * 
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks a task as completed based on index.
     * 
     * @param i the index of the task to mark as completed.
     */
    public void markTask(int i) {
        assert i >= 0;
        this.tasks.get(i).mark();
        Storage.updateSaved(tasks);
    }

    /**
     * Unmarks a completed task based on index.
     * 
     * @param i the index of the task to unmark.
     */
    public void unmarkTask(int i) {
        assert i >= 0;
        this.tasks.get(i).unmark();
        Storage.updateSaved(tasks);
    }

    /**
     * Returns a string representing the number of tasks in the list.
     * 
     * @return a string indicating the number of tasks in the list.
     */
    public String getListCounter() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * Returns the String representation of the task list which will be filtered
     * based on the keyword.
     * 
     * @param keyword the keyword to search for in the task description.
     * @return the String representation of the filtered tasks.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> filteredTasks = this.tasks.stream()
                .filter(t -> t.isRelevantTask(keyword))
                .collect(Collectors.toCollection(ArrayList::new));

        return toString(filteredTasks);
    }

    /**
     * Returns a string representing the tasks in the list.
     * 
     * @return a string indicating all tasks in the list.
     */
    @Override
    public String toString() {
        String taskStr = "Here are the tasks in your list:";
        for (int i = 0; i < this.tasks.size(); i++) {
            int num = i + 1;
            String nextTask = num + ". " + this.tasks.get(i);
            taskStr += "\n" + nextTask;
        }
        return taskStr;
    }

    private String toString(ArrayList<Task> tasks) {
        String taskStr = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            String nextTask = num + ". " + tasks.get(i);
            taskStr += "\n" + nextTask;
        }
        return taskStr;
    }
}
