import java.util.ArrayList;

public class ToDo {

    protected ArrayList<Task> tasks;

    protected static int tasksCount = 0;

    public ToDo() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        tasksCount ++;
    }

    public void listTask() {
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1)+ ". " + tasks.get(i).getDescription());
        }
    }

}
