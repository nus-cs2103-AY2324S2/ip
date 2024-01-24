import java.util.ArrayList;

public class TaskList {
    ArrayList<String> tasks;

    public TaskList() {
        tasks = new ArrayList<>(100);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addElements(String str) {
        tasks.add(str);
    }

    public void printElements() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
