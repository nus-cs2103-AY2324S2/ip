package earl.logic.stubs;

import earl.tasks.Task;

public class TaskStub extends Task {

    private int idx;

    public TaskStub() {
        super("TaskStub");
    }

    public TaskStub(int idx) {
        this();
        this.idx = idx;
    }

    @Override
    public boolean markAsDone() {
        return idx >= 0;
    }

    @Override
    public String toStorageString() {
        return "";
    }
}
