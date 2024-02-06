import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private int lastIdx = 0;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        this.lastIdx = taskArrayList.size();
    }

    public void addTask(Task task) {
        this.taskArrayList.add(task);
        this.lastIdx++;
    }

    public void removeTask(int i) {
        this.taskArrayList.remove(i);
        this.lastIdx--;
    }

    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    public Task getTaskByIdx(int idx) {
        return this.taskArrayList.get(idx);
    }

    public int getLastIdx() {
        return this.lastIdx;
    }


}
