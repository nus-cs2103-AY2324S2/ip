import java.util.*;
public class List {
    private ArrayList<String> tasks;

    public List(ArrayList<String> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void displayTasks() {
        System.out.println("__________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("__________________________________________________________\n");
    }
}
