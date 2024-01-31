import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this(new ArrayList<Task>());
    }

    private void add(Task task) {

    }

    private void delete(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }

        Task t = tasks.remove(taskNumber - 1);
        System.out.println("Ok, I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    private void mark(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }

        Task t = tasks.get(taskNumber - 1);
        t.mark();
        System.out.println("Nice, I've marked this task as done:");
        System.out.println(t);
    }

    private void unmark(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size()) {
            throw new DukeException("You do not have " + taskNumber + " tasks.");
        }

        Task t = tasks.get(taskNumber - 1);
        t.unmark();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(t);
    }
}
