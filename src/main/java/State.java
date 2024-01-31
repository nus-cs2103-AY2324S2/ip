import java.util.ArrayList;

public class State {
    ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int i) {
        if (i < 0 || i >= tasks.size()) {
            return null;
        }
        return tasks.get(i);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }
}
