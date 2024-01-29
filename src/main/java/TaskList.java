import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void add(Task taskToAdd) {
        this.taskList.add(taskToAdd);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public Task remove(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Marks the task with the given index as done.
     */
    public void setDone(int taskIndex, boolean isDone) {
        this.taskList.get(taskIndex - 1).setDone(isDone);
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    public String summary() {
        int listSize = this.taskList.size();

        // "task" for singular, "tasks" for plural
        if (listSize == 1) {
            return "You now have " + listSize + " task in your list.";
        } else {
            return "You now have " + listSize + " tasks in your list.";
        }
    }
}
