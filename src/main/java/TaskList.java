import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public void addTask(Task task) {
        add(task);
        System.out.println("_________________________________________\n");
        System.out.println("added: " + task.getDescription());
        System.out.println("_________________________________________\n");
    }

    public Task getTask(int index) {
        return get(index);
    }
    public void displayTasks() {
        if (isEmpty()) {
            System.out.println("_________________________________________\n");
            System.out.println("No pending tasks, congrats!");
            System.out.println("_________________________________________\n");
        } else {
            System.out.println("_________________________________________\n");
            for (int i = 0; i < size(); i++) {
                System.out.println((i + 1) + ". " + getTask(i));
            }
            System.out.println("_________________________________________\n");
        }
    }

}
