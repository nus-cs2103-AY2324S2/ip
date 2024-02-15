package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Represents a list of tasks. This class is responsible for managing tasks,
 * including adding, deleting, and completing tasks, as well as retrieving
 * information about the tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with a pre-existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param taskIndex The index of the task to be removed.
     */
    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Marks a task at the specified index as completed.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public void completeTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markAsDone();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    /**
     * Prints all the tasks in the list to the console.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Loads a list of tasks into the task list.
     *
     * @param loadedTasks The list of tasks to be loaded.
     */
    public void loadTasks(List<Task> loadedTasks) {
        tasks.addAll(loadedTasks);
    }

    /**
     * Retrieves a task by its index in the list.
     *
     * @param index The index of the task.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    /**
     * Finds and returns a list of tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}