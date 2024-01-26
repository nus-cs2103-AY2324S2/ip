import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<Task> list;

    public TodoList() {
        this.list = new ArrayList<>(100);
    }

    public String[] addTodo(String description) {
        Task task = new Todo(description);
        this.list.add(task);
        return addedTask(task);
    }

    public String[] addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        this.list.add(task);
        return addedTask(task);
    }

    public String[] addEvent(String description, String from, String to) {
        Task task = new Event(description, from, to);
        this.list.add(task);
        return addedTask(task);
    }

    public String[] getList() {
        String[] output = new String[this.list.size()+1];
        output[0] = "Here are the tasks in your list:";
        for (int i = 1; i < this.list.size()+1; i++) {
            output[i] = i + "." + this.list.get(i-1);
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

    public String[] addedTask(Task task) {
        int size = this.list.size();
        return new String[]{"Got it. I've added this task:", "  " + task,
        String.format("Now you have %s %s in the list.", size, size < 2 ? "task" : "tasks")};
    }
}
