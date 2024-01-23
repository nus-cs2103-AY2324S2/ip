import java.util.ArrayList;

public class TaskManager {

    ArrayList<Task> userTasks = new ArrayList<>();

    public void addUserTask(String taskName) {
        Task newTask = new Task(taskName);
        this.userTasks.add(newTask);
    }

    public void addToDoTask(String taskName) {
        ToDoTask newTask = new ToDoTask(taskName);
        this.userTasks.add(newTask);
    }

    public void addDeadlineTask(String taskName, String deadline) {
        DeadlineTask newTask = new DeadlineTask(taskName, deadline);
        this.userTasks.add(newTask);
    }

    public void addEventTask(String taskName, String startDateTime, String endDateTime) {
        EventTask newTask = new EventTask(taskName, startDateTime, endDateTime);
        this.userTasks.add(newTask);
    }

    public Task getTask(int index){
        return this.userTasks.get(index);
    }

    public int getTotalTaskCount(){
        return this.userTasks.size();
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
