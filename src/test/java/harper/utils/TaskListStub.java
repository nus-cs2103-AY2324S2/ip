package harper.utils;

import harper.tasks.Task;

public class TaskListStub extends TaskList {
    public boolean isAddTaskCalled;

    public TaskListStub() {
        super();
        isAddTaskCalled = false;
    }

    @Override
    public void addTask(Task task) {
        isAddTaskCalled = true;
    }
}
