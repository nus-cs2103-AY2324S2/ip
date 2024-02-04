package earl.logic.stubs;

import earl.util.TaskList;

public class TaskListStub extends TaskList {
    @Override
    public boolean mark(int idx) {
        return idx >= 0;
    }
}
