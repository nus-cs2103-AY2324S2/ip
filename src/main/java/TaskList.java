import java.util.ArrayList;

public class TaskList extends ArrayList<String> {

    public void addTask(String task) {
        add(task);
        System.out.println("added: " + task);
    }

    public void displayTasks() {
        if (isEmpty()) {
            System.out.println("No pending tasks, congrats!");
        } else {
            for (int i = 0; i < size(); i++) {
                System.out.println((i + 1) + ". " + get(i));
            }
        }
    }

}
