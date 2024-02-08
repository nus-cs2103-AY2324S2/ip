package youdon;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void remove(int i) {
        taskList.remove(i);
    }

    public void add(Task task) {
        taskList.add(task);
    }

}
