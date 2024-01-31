package SlayBot;

import entity.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class contains the task list and provides operations
 * to manipulate the Task objects.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Retrieves the list of Task objects in the TaskList.
     *
     * @return The List of Task objects in the TaskList.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Removes a Task object from the TaskList at the given index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Adds a task, t, to the TaskList.
     *
     * @param t The Task object to be added to the TaskList.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Helper function to iterate through the tasks in the TaskList and prints them with their
     * corresponding indices.
     */
    public void iterate() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    /**
     * Marks a Task object in the TaskList as completed.
     *
     * @param index The index of the task to be marked as completed.
     */
    public void markTask(int index) {
        Task taskToMark = tasks.get(index);
        taskToMark.setMarked(true);
        System.out.println("____________________________________________________________"
                + "\nNice! I've marked this task as done:\n" + taskToMark.toString()
                + "\n" + "____________________________________________________________");
    }

    /**
     * Unmarks a Task object in the TaskList as not completed.
     *
     * @param index The index of the task to be marked as not completed.
     */
    public void unmarkTask(int index) {
        Task taskToUnmark = tasks.get(index);
        taskToUnmark.setMarked(false);
        System.out.println("____________________________________________________________"
                + "\nOK, I've marked this task as not done yet:\n" + taskToUnmark.toString()
                + "\n" + "____________________________________________________________");
    }

}
