import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void printList() {
        Reply replyToUser = new PrintList(this.tasks);
        replyToUser.displayMessage();
    }
}
