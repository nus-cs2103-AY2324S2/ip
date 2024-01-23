import java.util.*;
public class TaskList {
    private ArrayList<Task> taskList;
    private int taskCount;

    TaskList() {
        this.taskList = new ArrayList<Task>();
        this.taskCount = 0;
    }

    public void addTask(Task t) {
        this.taskList.add(t);
        this.taskCount++;
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public int getCount() {
        return this.taskCount;
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < this.taskList.size(); i++) {
            output.append(String.format("%d. %s", i + 1, this.taskList.get(i)));
            if (i < this.taskList.size() - 1) output.append("\n");
        }
        return output.toString();
    }
}
