package chatbot.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    
    private ArrayList<Task> tasks;
    private int numTasks;
    private boolean isSaved;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numTasks = 0;
        this.isSaved = false;
    }

    public boolean addTask(Task task) {
        this.tasks.add(task);
        this.numTasks++;
        this.isSaved = false;
        return true;
    }

    public Task deleteTask(int index) {
        Task task = this.tasks.remove(index);
        this.numTasks--;
        this.isSaved = false;
        return task;
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
        this.isSaved = false;
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        this.isSaved = false;
        return task;
    }

    public int getNumTasks() {
        return this.numTasks;
    }

    public boolean isSaved() {
        return this.isSaved;
    }

    public void save() {
        this.isSaved = true;
    }

}
