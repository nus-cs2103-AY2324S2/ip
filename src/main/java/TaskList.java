
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    ArrayList<Task> getTasks() {
        return tasks;
    }

    Task get(int zeroItem) {
        return tasks.get(zeroItem);
    }

    int getSize() {
        return tasks.size();
    }

    void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as done based on the provided task number.
     *
     * @param num The task number to mark as done.
     */
    void markAsDone(int num) {
        tasks.get(num).markAsDone();
    }

    /**
     * Marks a task as not done based on the provided task number.
     *
     * @param num The task number to mark as not done.
     */
     void unMarkAsDone(int num) {
        tasks.get(num).unMarkAsDone();
    }

     void deleteTask(int item) throws DukeException {
        if (item >= 0 && item < tasks.size() && tasks.get(item) != null) {
            tasks.remove(item);
        } else {
            int oneItem = item + 1;
            throw new DukeException("\nError! Task number '" + oneItem + "' does not exist.\n");
        }
    }

}
