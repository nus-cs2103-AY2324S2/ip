import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }
}
