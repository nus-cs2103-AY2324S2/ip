import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getArraySize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(Task task) {
        task.markAsDone();
    }

    public void unmarkTask(Task task) {
        task.unmark();
    }


}
