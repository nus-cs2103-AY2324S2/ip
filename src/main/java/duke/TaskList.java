package duke;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private int length = 0;
    public TaskList() {
    }
    public void clear() {
        taskList.clear();
        length = 0;
    }
    public void add(Task t) {
        taskList.add(t);
        length = length + 1;
    }
    public Task delete(int numberToDelete) {
        if (numberToDelete < length && numberToDelete >= 0) {
            Task t = taskList.remove(numberToDelete);
            length = length - 1;
            return t;
        }
        return null;
    }
    public Task get(int numberToGet) {
        if (numberToGet < length && numberToGet >= 0) {
            return taskList.get(numberToGet);

        }
        return null;
    }
    public int length() {
        return length;
    }
}
