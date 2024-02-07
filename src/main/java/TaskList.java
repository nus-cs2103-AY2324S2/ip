import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void markTaskDone(int index) {
        list.get(index).markAsDone();
    }

    public void markTaskUndone(int index) {
        list.get(index).markAsUndone();
    }

    public void addTodo(Todo todo) {
        list.add(todo);
    }

    public void deleteTask(int index) {
        list.remove(index);
    }

    public int getSize() {
        return list.size();
    }
}
