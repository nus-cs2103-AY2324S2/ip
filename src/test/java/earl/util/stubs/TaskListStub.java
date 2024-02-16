package earl.util.stubs;

import earl.tasks.Task;
import earl.util.TaskList;

/**
 * Class acting as a stub for {@code TaskList}.
 */
public class TaskListStub extends TaskList {

    @Override
    public int getSize() {
        return 10;
    }

    @Override
    public Task get(int idx) {
        return new TaskStub(idx);
    }

    @Override
    public Task add(Task task) {
        return new TaskStub();
    }

    @Override
    public Task delete(int idx) {
        return new TaskStub();
    }
}
