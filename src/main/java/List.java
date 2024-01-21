import java.util.*;
public class List {
    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void displayTasks() {
        System.out.println("__________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("__________________________________________________________\n");
    }
}
