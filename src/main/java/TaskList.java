import java.util.*;
public class TaskList {
    private ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
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
