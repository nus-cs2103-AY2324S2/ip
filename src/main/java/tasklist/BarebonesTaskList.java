package tasklist;
import task.Task;
import java.util.ArrayList;
/**
 * The tasklist class is a representation of a list of tasks that is able to add, delete, mark and unmark tasks on that
 * list and return an appropriate response
 */
public class BarebonesTaskList {
    /** tracks number of tasks created*/
    int taskCount = 0;
    /** ArrayList<task> to store tasks */
    ArrayList<Task> taskList = new ArrayList<Task>();

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Retrieves task at index i
     *
     * @param i
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        taskList.add(task);
        taskCount += 1;
    }

    public void load(Task task) {
        taskList.add(task);
        taskCount += 1;
    }

    public void clear() {
        taskList.clear();
        taskCount = 0;
    }

    public void mark(int i){
        taskList.get(i - 1).markAsDone();
    }

    public void unmark(int i) {
        taskList.get(i - 1).unMarkAsDone();
    }

    public void delete(int i) {
        try {
            this.taskList.remove(i - 1);
            this.taskCount -= 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            // exception handling when taskList is empty or invalid index is required
            System.out.println(e);
        }
    }

    /**
     * Prints the list of tasks
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        if (taskCount == 0) {
            return "Sorry Sir/Mdm, it looks as though you have yet to add any tasks";
        }
        for (int i = 0;i < taskCount; i++) {
            text.append(String.format("%d. %s \n", i + 1, this.taskList.get(i)));
        }
        return text.toString();
    }
}
