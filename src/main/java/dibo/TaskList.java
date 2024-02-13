package dibo;

import java.util.ArrayList;

import dibo.exception.DiboException;
import dibo.task.Task;

/**
 * Class to store all the tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int size;

    /**
     * Constructs the TaskList class.
     *
     * @param storage The ArrayList returned from loading the text file.
     */
    public TaskList(ArrayList<Task> storage) {
        assert storage != null : "The ArrayList should not be null";
        this.tasks = storage;
        this.size = storage.size();
    }

    /**
     * Takes in a task and adds it into the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.size++;
        assert this.tasks.contains(task) : "The taskList should contain the added task";
    }

    /**
     * Returns the string representation of the list to be displayed.
     */
    public String getDisplayFormat() {
        StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < size; ++i) {
            int taskIndex = i + 1;
            String taskToString = tasks.get(i).toString();
            String task = String.format("%d.%s\n", taskIndex, taskToString);
            list.append(task);
        }
        return list.toString();
    }

    /**
     * Returns the string representation of the list to be saved.
     */
    public String getSaveFormat() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            Task task = tasks.get(i);
            list.append(task.getSaveFormat());
            list.append("\n");
        }
        return list.toString();
    }

    /**
     * Returns the string representation of the tasks with the specified keyword.
     *
     * @throws DiboException if there is no such task with the specified keyword.
     */
    public String getTasksWithKeyword(String[] keywords) throws DiboException {
        StringBuilder list = new StringBuilder();
        int taskCount = 0;
        for (int i = 0; i < size; ++i) {
            Task task = tasks.get(i);
            if (task.hasKeywords(keywords)) {
                taskCount++;
                String taskToString = tasks.get(i).toString();
                String taskString = String.format("%d.%s\n", taskCount, taskToString);
                list.append(taskString);
            }
        }

        if (taskCount == 0) {
            throw new DiboException("Oh no sir! We cannot find any task with the specified keywords :(");
        }

        return list.toString();
    }

    /**
     * Takes in an index, marks the task at that index as done
     * and return the string representation of that task.
     *
     * @param i The index of the task.
     * @return The string representation of the task.
     * @throws DiboException if there is no such task with the index.
     */
    public String markTask(int i) throws DiboException {
        int taskIndex = i - 1;
        if (taskIndex >= this.size || taskIndex < 0) {
            throw new DiboException("Oh no sir! The index you provided is out of bounds");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        assert task.isDone() : "This task should be marked as done.";
        return task.toString();
    }

    /**
     * Takes in an index, marks the task at that index as not done
     * and return the string representation of that task.
     *
     * @param i The index of the task.
     * @return The string representation of the task.
     * @throws DiboException if there is no such task with the index.
     */
    public String unmarkTask(int i) throws DiboException {
        int taskIndex = i - 1;
        if (taskIndex >= this.size || taskIndex < 0) {
            throw new DiboException("Oh no sir! The index you provided is out of bounds");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        assert !task.isDone() : "This task should not be marked as done.";
        return task.toString();
    }

    /**
     * Takes in an index, deletes the task at that index
     * and return the string representation of that task.
     *
     * @param i The index of the task.
     * @return The string representation of the task.
     * @throws DiboException if there is no such task with the index.
     */
    public String deleteTask(int i) throws DiboException {
        int taskIndex = i - 1;
        if (taskIndex >= this.size || taskIndex < 0) {
            throw new DiboException("Oh no sir! The index you provided is out of bounds");
        }
        Task task = tasks.get(taskIndex);
        tasks.remove(i - 1);
        this.size--;
        assert !this.tasks.contains(task) : "The taskList should not longer contain the added task";
        return task.toString();
    }

    /**
     * Returns the size of the TaskList to be shown to the user.
     */
    public int getSize() {
        return this.size;
    }
}
