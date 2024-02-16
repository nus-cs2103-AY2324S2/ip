package earl.util.stubs;

import earl.tasks.Task;

/**
 * Class acting as a stub for {@code Task}.
 */
public class TaskStub extends Task {

    private int idx;

    /** Sets description as TaskStub. */
    public TaskStub() {
        super("TaskStub");
    }

    /** Constructor specifying index to later mark as done. */
    public TaskStub(int idx) {
        this();
        this.idx = idx;
    }

    @Override
    public boolean markAsDone() {
        return idx >= 0;
    }

    @Override
    public String toString() {
        return getDescription() + "," + getStatusIcon();
    }

    @Override
    public String toStorageString() {
        return "";
    }
}
