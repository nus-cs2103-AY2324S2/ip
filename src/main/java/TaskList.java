package task;

import java.util.ArrayList;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import ui.Ui;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int taskNumber) {
        return taskList.get(taskNumber);
    }

    public void set(int taskNumber, Task task) {
        taskList.set(taskNumber, task);
    }

    public void remove(int taskNumber) {
        taskList.remove(taskNumber);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

}
