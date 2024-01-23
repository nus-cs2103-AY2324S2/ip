import java.util.ArrayList;

public class Memory {
    
    private ArrayList<Task> tasks;
    private int numTasks;

    Memory() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
    }

    public boolean add(Task task) {
        this.tasks.add(task);
        this.numTasks++;
        return true;
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : this.tasks) {
            list.add(task);
        }
        return list;
    }

    public Task markTask(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return task;
    }

    public int getNumTasks() {
        return this.numTasks;
    }

}
