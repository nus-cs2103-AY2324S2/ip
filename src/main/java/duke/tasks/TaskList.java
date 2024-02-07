package duke.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 5L;

    private final List<Task> tasks = new ArrayList<>();


    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int i) {
        tasks.remove(i);
    }
}