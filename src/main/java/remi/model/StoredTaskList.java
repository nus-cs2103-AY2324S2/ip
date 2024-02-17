package remi.model;

import remi.storage.Storage;
import remi.utils.RemiError;

/**
 * A TaskList variant that writes to Storage any time an update is made.
 */

public class StoredTaskList extends TaskList {
    public StoredTaskList() {
        super();
    }

    @Override
    public void addTask(Task t) {
        super.addTask(t);
        Storage.store(this);
    }

    @Override
    public void markTask(int idx) {
        super.markTask(idx);
        Storage.store(this);
    }

    @Override
    public void unmarkTask(int idx) {
        super.unmarkTask(idx);
        Storage.store(this);
    }

    @Override
    public void removeTask(int idx) throws RemiError {
        super.removeTask(idx);
        Storage.store(this);
    }
}
