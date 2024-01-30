import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> taskList){
        this.taskList = taskList;
    }

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }
}
