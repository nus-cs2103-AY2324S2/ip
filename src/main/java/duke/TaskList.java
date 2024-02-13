package duke;

import java.util.ArrayList;

/**
 * TaskList class to handle operations on the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initializes a TaskList object with a list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     * Returns false if there is no Task given.
     *
     * @param task the task to be added
     * @return true if the task is added successfully
     */
    public boolean addTask(Task task) {
        if (task == null) {
            return false;
        }
        return tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param index the index of the task to be deleted
     * @return the task that is deleted
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets a task from the list of tasks.
     *
     * @param index the index of the task to be retrieved
     * @return the task at the index
     * @throws DukeException if the task does not exist at the index
     */
    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("No task found at index " + index + ".");
        }
        return tasks.get(index);
    }

    /**
     * Gets the size of the list of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as done.
     */
    public Task markDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done yet.
     */
    public Task unmarkDone(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Finds tasks with the description that contains the keyword given by user.
     *
     * @param description the keyword to be searched for
     * @return a list of tasks that contain the description
     */
    public TaskList findTasks(String description) {
        assert description != null : "Description should not be null";
        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }
}
