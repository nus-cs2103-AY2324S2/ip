import java.util.ArrayList;

public class TaskManager {

    ArrayList<String> userTasks = new ArrayList<>();

    public void addUserTask(String task) {
        this.userTasks.add(task);
    }

    public ArrayList<String> getUserTasks(){
        return userTasks;
    }
}
