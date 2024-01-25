import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        this.list.add(t);
    }

    public String toString() {
        StringBuilder displayString = new StringBuilder();
        int count = 1;
        for (Task t: list) {
            displayString.append(count).append(". ").append(t.getDescription()).append("\n");
            count++;
        }
        return displayString.toString();
    }
}
