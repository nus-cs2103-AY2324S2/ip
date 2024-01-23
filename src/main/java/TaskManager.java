import java.util.ArrayList;

public class TaskManager {

    ArrayList<Task> userTasks = new ArrayList<>();

    public void addUserTask(String task) {
        Task newTask = new Task(task);
        this.userTasks.add(newTask);
    }

    public ArrayList<Task> getUserTasks(){
        return userTasks;
    }
}
