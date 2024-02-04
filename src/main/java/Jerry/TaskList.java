package Jerry;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {

        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public String printTask(int index) {
        return tasks.get(index).toString();
    }

    public void mark(int index) {
        tasks.get(index).markDone();
    }

    public void unmark(int index) {
        tasks.get(index).markNotDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
