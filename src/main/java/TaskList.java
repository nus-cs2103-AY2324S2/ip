import java.util.ArrayList;

public class TaskList {
    ArrayList<String> list = new ArrayList<>();

    public void addTask(String task) {
        this.list.add(task);
        System.out.println("Added: " + task);
    }

    public void printList() {
        for (int i = 1; i < this.list.size() + 1; i++) {
            System.out.println(i + ". " + this.list.get((i - 1)));
        }
    }

}
