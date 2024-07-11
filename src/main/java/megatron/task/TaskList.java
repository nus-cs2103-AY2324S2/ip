package megatron.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import megatron.data.exception.DukeException;

/**
 * TaskList manages operations relating
 * to the list of tasks
 */
public class TaskList {

    /** List of tasks */
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns list of tasks
     * @return List of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns number of tasks
     *
     * @return Number of tasks in the list
     */
    public int getNumTasks() {
        return tasks.size();
    }

    /**
     * Adds new task to the list
     *
     * @param newTask task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes task at given index from the list
     *
     * @param deleteIndex index of task to be deleted
     * @return Task that was deleted
     * @throws DukeException if number given is out of bounds
     */
    public Task deleteTask(int deleteIndex) throws DukeException {
        if (deleteIndex > tasks.size() || deleteIndex <= 0) {
            throw new DukeException("\tNumber out of bounds!\n");
        }
        return tasks.remove(deleteIndex - 1);
    }

    /**
     * Mark or unmark task of given update index
     *
     * @param updateIndex index of task to be updated
     * @param taskComplete to mark or unmark the task
     * @return Task that was updated
     * @throws DukeException if number given is out of bounds
     */
    public Task markTask(int updateIndex, boolean taskComplete)
            throws DukeException {
        if (updateIndex > tasks.size() || updateIndex <= 0) {
            throw new DukeException("Number Out of Bounds");
        }
        Task updateTask = tasks.get(updateIndex - 1);

        if (taskComplete) {
            updateTask.setMarked();
        } else {
            updateTask.setNotMarked();
        }
        return updateTask;
    }

    /**
     * Returns list of tasks with given search string
     *
     * @param searchString keywords to search for
     * @return List of tasks with given keyword
     */
    public List<Task> search(String searchString) {
        return tasks.stream()
                .filter(task -> task.getDetails().contains(searchString))
                .collect(Collectors.toList());
    }

}
