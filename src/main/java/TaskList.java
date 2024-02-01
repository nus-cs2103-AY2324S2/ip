import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this(new ArrayList<Task>());
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public String delete(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }
        Task t = tasks.remove(taskNumber - 1);
        return t.toString();
    }

    public String mark(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }
        Task t = tasks.get(taskNumber - 1);
        t.mark();
        return t.toString();
    }

    public String unmark(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }

        Task t = tasks.get(taskNumber - 1);
        t.unmark();
        return t.toString();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    public int size() {
        return this.tasks.size();
    }
}
