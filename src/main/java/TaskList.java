import java.util.ArrayList;



public class TaskList {

    public ArrayList<Task> tasks;

    public  TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
