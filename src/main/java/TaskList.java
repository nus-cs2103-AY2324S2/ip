import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList.addAll(taskList);
    }
    public int getLength() {
        return this.taskList.size();
    }

    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int taskId) {
        this.taskList.remove(taskId - 1);
    }


    public Task get(int taskId) {
        return this.taskList.get(taskId - 1);
    }
}
