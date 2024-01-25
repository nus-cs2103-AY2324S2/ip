import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        this.list.add(t);
    }

    public int getLength() {
        return this.list.size();
    }

    public Task markTask(int i) {
        Task t = this.list.get(i);
        t.mark();
        return t;
    }

    public Task unmarkTask(int i) {
        Task t = this.list.get(i);
        t.unmark();
        return t;
    }

    @Override
    public String toString() {
        StringBuilder displayString = new StringBuilder();
        int count = 1;
        for (Task t: list) {
            displayString.append(count).append(". ").append(t.toString()).append("\n");
            count++;
        }
        return displayString.toString();
    }
}
