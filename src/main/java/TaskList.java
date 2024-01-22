import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        ArrayList<String> a = new ArrayList<String>();
        for (int i = 0; i < this.tasks.size(); i++) {
            a.add(String.format("%d. %s",
                    i + 1,
                    this.tasks.get(i).taskWithStatus()));
        }
        return String.join("\n", a);
    }
}
