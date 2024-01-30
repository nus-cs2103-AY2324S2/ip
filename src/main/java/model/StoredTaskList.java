package model;

import storage.Storage;
import utils.RemiError;

public class StoredTaskList extends TaskList {
    public StoredTaskList() {
        super();
    }

    /**
     * Add a task to the end of the TaskList.
     *
     * @param t task to be added
     */
    @Override
    public void addTask(Task t){
        super.addTask(t);
        Storage.store(this);
    }

    /**
     * Mark a task as done.
     *
     * @param idx the 1-indexed index of the task to be marked
     */
    @Override
    public void markTask(int idx) {
        super.markTask(idx);
        Storage.store(this);
    }

    /**
     * Mark a task as not done.
     *
     * @param idx the 1-indexed index of the task to be unmarked
     */
    @Override
    public void unmarkTask(int idx) {
        super.unmarkTask(idx);
        Storage.store(this);
    }

    /**
     * Removes a task.
     *
     * @param idx the 1-indexed index of the task to be removed
     * @throws RemiError if idx is invalid (idx must satisfy 1 <= idx <= size)
     */
    @Override
    public void removeTask(int idx) throws RemiError {
        super.removeTask(idx);
        Storage.store(super);
    }
}
