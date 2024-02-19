package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the Duke application.
 * Provides methods to manipulate the tasks list.
 */
public class TaskList {

    private List<Task> tasks; // The list of tasks
    public static int storageFill = 0; // A counter for the number of tasks

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Removes a task from the list at the specified index.
     * Also decrements the static storageFill counter.
     *
     * @param index The index of the task to be removed.
     */
    protected void deleteTask(int index) {
        int previousTasks = storageFill;
        tasks.remove(index);
        TaskList.storageFill--; // Update task count
        assert previousTasks == storageFill++ : "Task list size should decrease by 1";
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    protected Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    protected List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints all tasks in the list to the console.
     * Each task is printed with its list index.
     */
    public String listTasks() {
        StringBuilder output = new StringBuilder();
        output.append("\tHere are the tasks in your list:\n");
        for (int i = 0; i < TaskList.storageFill; i++) {
            String formattedOutput = String.format("\t%d. %s\n", (i + 1), this.tasks.get(i));
            output.append(formattedOutput);
        }
        return output.toString();
    }

    /**
     * Prints matching tasks in the list to the console.
     * Each task is printed with its list index. A task matches if its description
     * contains the findString.
     *
     * @param findString The string to search for within each task's description.
     */
    public String findTasks(String findString) {
        StringBuilder output = new StringBuilder();
        output.append("\tHere are the matching tasks in your list:\n");
        boolean isFound = false;

        for (int i = 0; i < TaskList.storageFill; i++) {
            Task currTask = this.tasks.get(i);
            if (currTask.getDetails().toLowerCase().contains(findString.toLowerCase())) {
                String formattedOutput = String.format("\t%d. %s\n", (i + 1), currTask);
                output.append(formattedOutput);
                isFound = true;
            }
        }

        if (!isFound) {
            output.append("\tNo tasks match your search criteria.");
        }
        return output.toString();
    }

    /**
     * Adds a task to the list and increments the static storageFill counter.
     *
     * @param task The task to add to the list.
     */
    protected void addTask(Task task) {
        int previousTasks = storageFill;
        tasks.add(task);
        storageFill++;
        assert previousTasks == storageFill-- : "Task list size should increase by 1";
    }
}
