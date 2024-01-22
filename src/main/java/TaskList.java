import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<String> taskArray;

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    public void add(String item) {
        taskArray.add(item);
    }

    public void display() {
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println((i + 1) + ". " + taskArray.get(i));
        }
    }
}
