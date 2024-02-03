import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() { }

    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    public String deleteTask(int index) {
        return tasks.remove(index - 1).toString();
    }

    public String markTask(int index) {
        tasks.get(index - 1).mark();
        return tasks.get(index - 1).toString();
    }

    public String unmarkTask(int index) {
        tasks.get(index - 1).unmark();
        return tasks.get(index - 1).toString();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public String storeTasks() {
        StringBuilder savedTask = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            savedTask.append(tasks.get(i).storeData()).append("\n");
        }
        return savedTask.toString();
    }

    @Override
    public String toString() {
        StringBuilder tasksStr = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            tasksStr.append("     ").append(i).append(".")
                    .append(tasks.get(i - 1).toString()).append("\n");
        }
        return tasksStr.toString();
    }
}
