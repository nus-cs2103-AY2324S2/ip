import java.util.ArrayList;
import java.util.List;

public class Todo {

    private List<Task> list;

    public Todo() {
        this.list = new ArrayList<>(100);
    }

    public String add(String item) {
        Task task = new Task(item);
        this.list.add(task);
        return "added: " + item;
    }

    public String[] getList() {
        String[] output = new String[this.list.size()];
        for (int i = 0; i < this.list.size(); i++) {
            output[i] = i+1 + ". " + this.list.get(i);
        }
        return output;
    }

    public String[] mark(int i) {
        Task task = this.list.get(i-1);
        String description = task.markAsDone();
        return new String[]{"Nice! I've marked this task as done:", "  " + description};
    }

    public String[] unmark(int i) {
        Task task = this.list.get(i-1);
        String description = task.markAsNotDone();
        return new String[]{"OK, I've marked this task as not done yet:", "  " + description};
    }
}
