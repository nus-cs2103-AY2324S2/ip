import java.util.ArrayList;
import java.util.List;
public class TaskList extends ArrayList<Task> {
    public TaskList() {
    }

    public TaskList(List<Task> tasks) {
        super(tasks);
    }
}
