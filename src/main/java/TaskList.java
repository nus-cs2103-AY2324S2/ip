import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTodoList() {
        return this.tasks;
    }

    public void printList() {
        // 1-indexed todolist
        for (int i = 1; i < tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i));
        }
    }
}
