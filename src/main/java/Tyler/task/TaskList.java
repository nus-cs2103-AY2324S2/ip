package tyler.task;

import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public int getNumOfTasks() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }
}
