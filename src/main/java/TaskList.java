import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
