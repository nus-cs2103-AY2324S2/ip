package earl.logic.stubs;

import earl.tasks.Task;
import earl.util.TaskList;

public class TaskListStub extends TaskList {

    @Override
    public int getSize() {
        return 10;
    }

    @Override
    public Task get(int idx) {
        return new TaskStub(idx);
    }
}
