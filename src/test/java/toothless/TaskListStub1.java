package toothless;

import toothless.tasks.Task;
import toothless.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskListStub1 extends TaskList{
    public TaskListStub1() {
        super();
        Task task = new Todo("Eat sandwich");
        super.addTask(task);
    }
}
