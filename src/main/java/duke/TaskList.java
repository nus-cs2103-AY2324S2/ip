package duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<duke.Task> listArr;

    public TaskList(ArrayList<duke.Task> listArr) {
        this.listArr = listArr;
    }

    public int size() {
        return this.listArr.size();
    }

    public duke.Task get(int idx) {
        return this.listArr.get(idx);
    }

    public void setDone(int idx) {
        this.get(idx).setDone();
    }

    public void setUndone(int idx) {
        this.get(idx).setUndone();
    }

    public void deleteTask(int idx) {
        this.listArr.remove(idx);
    }

    public void addTask(Task t) {
        this.listArr.add(t);
    }

    public String taskToString(int idx) {
        return this.get(idx).toString();
    }


}
