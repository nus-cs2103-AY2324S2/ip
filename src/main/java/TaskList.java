import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTasks(int index) {
        return tasks.get(index);
    }

    public void saveTask(Storage s) {
        s.saveTasks(tasks);
    }

    public void printList(int taskCount) {
        for (Task task : tasks) {
            System.out.println(" " + taskCount + "." + task);
            taskCount++;
        }
    }

}
