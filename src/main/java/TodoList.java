import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<Task> list;

    public TodoList() {
        this.list = new ArrayList<>(100);
    }

    public String[] add(Task task) {
        this.list.add(task);
        int size = this.list.size();
        return new String[]{"Got it. I've added this task:", "  " + task,
                String.format("Now you have %s %s in the list.", size, size < 2 ? "task" : "tasks")};
    }

    public String[] addTodo(String description) {
        Task task = new Todo(description);
        return add(task);
    }

    public String[] addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        return add(task);
    }

    public String[] addEvent(String description, String from, String to) {
        Task task = new Event(description, from, to);
        return add(task);
    }

    public String[] getList() {
        if (this.list.size() == 0) {
            return new String[]{"Your list is currently empty."};
        }
        String[] output = new String[this.list.size()+1];
        output[0] = "Here are the tasks in your list:";
        for (int i = 1; i < this.list.size()+1; i++) {
            output[i] = i + "." + this.list.get(i-1);
        }
        return output;
    }

    public String[] mark(int i) throws ParameterException {
        if (i > this.list.size() || i < 1) {
            throw new ParameterException("Please select a valid task number from the list.");
        }
        Task task = this.list.get(i-1);
        String description = task.markAsDone();
        return new String[]{"Nice! I've marked this task as done:", "  " + description};
    }

    public String[] unmark(int i) throws ParameterException {
        if (i > this.list.size() || i < 1) {
            throw new ParameterException("Please select a valid task number from the list.");
        }
        Task task = this.list.get(i-1);
        String description = task.markAsNotDone();
        return new String[]{"OK, I've marked this task as not done yet:", "  " + description};
    }
}
