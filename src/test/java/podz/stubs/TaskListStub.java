package podz.stubs;

import java.util.ArrayList;

import podz.task.Task;
import podz.task.TaskList;
import podz.task.Todo;

public class TaskListStub extends TaskList {
    public TaskListStub(ArrayList<Task> tasks) {
        super(tasks);
    }
    
    @Override
    public void deleteTask(int i) {
        super.tasks.remove(i);
    }

    @Override
    public void markTask(int i) {
        super.tasks.get(i).mark();
    }

    public void initDataThreeTasks() {
        super.tasks.add(new Todo("eat chicken"));
        super.tasks.add(new Todo("sleep"));
        super.tasks.add(new Todo("read"));
    }

    public ArrayList<Task> getTaskList() {
        return super.tasks;
    }
}
