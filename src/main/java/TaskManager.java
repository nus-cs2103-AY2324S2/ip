import java.util.ArrayList;

public class TaskManager {

    ArrayList<Task> userTasks = new ArrayList<>();

    public void addUserTask(String task) {
        Task newTask = new Task(task);
        this.userTasks.add(newTask);
    }

    public Task getTask(int index){
        return this.userTasks.get(index);
    }
    public void markTaskCompleted(int index) {
        this.userTasks.get(index).markCompleted();
    }

    public void markTaskIncomplete(int index) {
        this.userTasks.get(index).markIncomplete();
    }

    public ArrayList<Task> getUserTasks(){
        return userTasks;
    }
}
