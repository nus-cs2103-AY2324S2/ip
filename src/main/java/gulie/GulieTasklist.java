package gulie;
import gulie.task.Task;

import java.util.ArrayList;

/**
 * Contains Tasks for Gulie.
 */
public class GulieTasklist extends ArrayList<Task> {
    /**
     * Stores a task.
     * @param task
     * @return
     */
    public Task store(Task task) {
        this.add(task);
        return task;
    }

    /**
     * Deletes a task.
     * @param i The index of the task to be deleted.
     * @return
     * @throws GulieException If an invalid index is given.
     */
    public Task delete(int i) throws GulieException {
        if (i >= this.size() || i < 0) {
            throw new GulieException("Invalid index: " + i);
        }
        Task task = this.get(i);
        return this.remove(i);
    }

    /**
     * Marks a task as complete.
     * @param i The index of the task to be marked as complete.
     * @throws GulieException If an invalid index is given.
     */
    public void mark(int i) throws GulieException {
        if (i >= this.size() || i < 0)
            throw new GulieException("Invalid index: " + i);
        Task task = this.get(i);
        task.setMark(true);
    }

    /**
     * Marks a task as incomplete.
     * @param i The index of the task to be marked as incomplete.
     * @throws GulieException If an invalid index is given.
     */
    public void unmark(int i) throws GulieException {
        if (i >= this.size() || i < 0)
            throw new GulieException("Invalid index: " + i);
        Task task = this.get(i);
        task.setMark(false);
    }
}
