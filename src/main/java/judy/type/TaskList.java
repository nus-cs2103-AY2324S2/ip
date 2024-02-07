package judy.type;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task get(int taskId) {
        return this.taskList.get(taskId);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(Task task) {
        this.taskList.remove(task);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
