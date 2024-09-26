package adam.task;

import java.util.ArrayList;

import adam.AdamException;

/**
 * Represents a list of tasks to be stored.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Returns a list of tasks containing the tasks provided.
     *
     * @param tasks The tasks to store in the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an empty list of tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        int before = tasks.size();
        tasks.add(task);
        assert tasks.size() == before + 1 : "task was not added";
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param taskNumber The index of the task to delete.
     * @return The String format of the task deleted.
     * @throws AdamException If the index provided is greater than the list of tasks.
     */
    public String delete(int taskNumber) throws AdamException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new AdamException("You do not have " + taskNumber + " tasks.");
        }
        int before = tasks.size();
        Task t = tasks.remove(taskNumber - 1);
        assert tasks.size() == before - 1 : "task was not deleted";
        return t.toString();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param taskNumber The index of the task to mark as done.
     * @return The string format of the task marked as done.
     * @throws AdamException If the index provided is greater than the list of tasks.
     */
    public String mark(int taskNumber) throws AdamException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new AdamException("You do not have " + taskNumber + " tasks.");
        }

        Task t = tasks.get(taskNumber - 1);
        t.mark();
        assert t.isDone : "task not marked as done";
        return t.toString();
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param taskNumber The index of the task to mark as not done.
     * @return The String format of the task marked as not done.
     * @throws AdamException If the index provided is greater than the list of tasks.
     */
    public String unmark(int taskNumber) throws AdamException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new AdamException("You do not have " + taskNumber + " tasks.");
        }

        Task t = tasks.get(taskNumber - 1);
        t.unmark();
        assert !t.isDone : "task not marked as undone";
        return t.toString();
    }

    /**
     * Returns a list of tasks that contains the given keyword.
     *
     * @param keyword The keyword to find tasks with.
     * @return A list of tasks containing the given keyword.
     */
    public ArrayList<String> find(String keyword) {
        ArrayList<String> found = new ArrayList<>();
        for (Task t : tasks) {
            if (t.canFind(keyword)) {
                found.add(t.toString());
            }
        }
        return found;
    }

    /**
     * Returns the list of tasks in an ArrayList.
     *
     * @return The list of tasks in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }
}
