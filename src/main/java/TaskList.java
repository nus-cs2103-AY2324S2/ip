import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTodoList() {
        return this.tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkAsDone(int index) {
        tasks.get(index).unmarkAsDone();
    }

    public void printList() {
        // 1-indexed todolist
        for (int i = 1; i < tasks.size(); i++) {
            System.out.println(i + "." + tasks.get(i));
        }
    }
}
