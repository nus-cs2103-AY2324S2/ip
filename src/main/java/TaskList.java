import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return  tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public List<Task> getList() {
        return tasks;
    }
    public void mark(int taskNumber) {
        Task task = this.tasks.get(taskNumber - 1);
        task.done();
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public void delete(int taskNumber) {
        Task removedTask = this.tasks.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.\n");
    }



}
