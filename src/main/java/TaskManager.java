import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    public TaskManager() {
        this.taskList = new ArrayList<>(101);
        this.taskList.add(null); // First element left empty for 1-based indexing
    }

}
