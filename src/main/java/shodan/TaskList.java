package shodan;

import shodan.tasks.Task;

import java.util.List;

/**
 * Manages the list of {@link Task tasks}. Contains methods
 * to add, delete and update each task's status.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Instantiates a new TaskList.
     *
     * @param tasks the tasks to restore to the list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a Task to the list.
     *
     * @param task the task to add.
     * @return the current size of the list.
     */
    public int add(Task task) {
        tasks.add(task);
        return tasks.size();
    }

    /**
     * Delete the selected task from the list.
     *
     * @param index the index of the task to delete.
     * @return the deleted task.
     * @throws IndexOutOfBoundsException if the requested index is out of bounds.
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Mark the task as done or not done. Regardless of
     *
     * @param index the index of the task to mark.
     * @param done  chooses the behavior of the method.
     *              If True, the task will be marked as done, or not done if False.
     * @return the task that was marked.
     * @throws IndexOutOfBoundsException if the requested index is out of bounds.
     */
    public Task mark(int index, boolean done) throws IndexOutOfBoundsException {
        Task task = tasks.get(index);
        task.setDone(done);
        return task;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return the list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
