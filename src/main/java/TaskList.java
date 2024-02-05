import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>(101);
        this.taskList.add(null); // First element left empty for 1-based indexing
    }

}
