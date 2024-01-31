import java.util.*;
import java.io.*;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove(int i) {
        return tasks.remove(i);
    }

    public List<Task> getList() {
        return tasks;
    }

    public Task get(int i) {
        return tasks.get(i);
    }


}
