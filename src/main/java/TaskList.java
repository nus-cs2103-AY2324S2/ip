import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list = new ArrayList<>();

    public void addTask(String taskName) {
        this.list.add(new Task(taskName));
        System.out.println("Added: " + taskName);
    }
    private Task getTask(int i) {
        return this.list.get(i);
    }
    public void printList() {
        for (int i = 1; i < this.list.size() + 1; i++) {
            System.out.println(i + ". " + getTask(i - 1).getRep());
        }
    }
    public void mark(int i) {
        Task task = getTask(i - 1);
        task.mark();
        System.out.println(task.getRep());
    } // Handle index exception here maybe
    public void unmark(int i) {
        Task task = getTask(i - 1);
        task.unmark();
        System.out.println(task.getRep());
    }
}
