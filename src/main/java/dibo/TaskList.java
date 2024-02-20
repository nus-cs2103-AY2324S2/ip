package dibo;

import java.util.ArrayList;

import dibo.exception.DiboException;
import dibo.task.Task;

/**
 * The TaskList class represents the entity to keep all the tasks of the user.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int size;

    /**
     * Constructs a new TaskList object.
     *
     * @param storage The ArrayList which contains all the current tasks of the user.
     *                It is loaded from the text file.
     */
    public TaskList(ArrayList<Task> storage) {
        assert storage != null : "The ArrayList should not be null";
        this.tasks = storage;
        this.size = storage.size();
    }

    /**
     * Adds a new task into the TaskList.
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
        if (this.size == 0) {
            return "Sorry sir. There is currently not task in your list.";
        }
        String displayMessage = "Here are the tasks in your list:\n";
        StringBuilder list = new StringBuilder(displayMessage);

        for (int i = 0; i < this.size; ++i) {
            int taskIndex = i + 1;
            String taskToString = tasks.get(i).toString();
            String taskString = String.format("%d.%s\n", taskIndex, taskToString);
            list.append(taskString);
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
     * Returns the string representation of the tasks with the specified keyword(s).
     *
     * @throws DiboException If there is no such task with the specified keyword(s).
     */
    public String getTasksWithKeywords(String[] keywords) throws DiboException {
        StringBuilder list = new StringBuilder();

        int taskCount = 0;
        for (int i = 0; i < size; ++i) {
            Task task = tasks.get(i);
            if (!task.hasKeywords(keywords)) {
                continue;
            }

            taskCount++;
            String taskToString = tasks.get(i).toString();
            String taskString = String.format("%d.%s\n", taskCount, taskToString);
            list.append(taskString);
        }

        if (taskCount == 0) {
            throw new DiboException("Oh no sir! We cannot find any task with the specified keywords :(");
        }

        return list.toString();
    }

    /**
     * Marks the task at as done and returns the string representation of that task.
     *
     * @param i The index of the task, based 1.
     * @throws DiboException If there is no such task with the index.
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
     * Marks the task as not done and returns the string representation of that task.
     *
     * @param i The index of the task, based 1.
     * @throws DiboException If there is no such task with the index.
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
     * Deletes the task and returns the string representation of that task.
     *
     * @param i The index of the task, based 1.
     * @throws DiboException If there is no such task with the index.
     */
    public String deleteTask(int i) throws DiboException {
        int taskIndex = i - 1;
        if (taskIndex >= this.size || taskIndex < 0) {
            throw new DiboException("Oh no sir! The index you provided is out of bounds");
        }
        Task task = tasks.get(taskIndex);

        tasks.remove(i - 1);
        this.size--;
        assert !this.tasks.contains(task) : "The taskList should not longer contain the deleted task";

        return task.toString();
    }

    /**
     * Returns the size of the TaskList.
     */
    public int getSize() {
        return this.size;
    }
}
