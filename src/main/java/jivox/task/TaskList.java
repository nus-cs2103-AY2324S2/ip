package jivox.task;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public void delete(int taskNum) {
        this.tasks.remove(taskNum);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum);
    }
}
