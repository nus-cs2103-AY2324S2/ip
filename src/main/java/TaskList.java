import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {

    }

    public static ArrayList<Task> getList() {
        return list;
    }

    public void addTask(Task task){
        list.add(task);
    }

    public void deleteTask(int index) {
        list.remove(index);
    }

    public Task getTask(int index) {
        return list.get(index);
    }
}
