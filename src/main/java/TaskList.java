import java.util.ArrayList;
/**
 * Represents the taskList to be used in the application.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the item at index from task list
     *
     * @param index index of task to delete as shown by list command
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        tasks.remove(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s \n", i + 1, tasks.get(i).toString()));
        }
        if (tasks.size() == 0) {
            sb.append("There are no tasks for you today. Enjoy :)");
        }
        return sb.toString();
    }
}