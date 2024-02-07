import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private static final int MAX_TASKS = 100;

    public int getSize() {
        return tasks.size();
    }

    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskIndexById(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                return i;
            }
        }
        return -1;
    }

    public int getTaskNumber(String task) {
        String[] word = task.split(" ");
        if (word.length == 2) {
            int taskId = Integer.parseInt(word[1]);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId() == taskId) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void updateTaskIds() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId(i + 1);
        }
    }

    public void addTask(Task task) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
        }
    }

    public Task deleteTask(int taskId) {
        if (taskId < 0 || taskId >= tasks.size()) {
            return null;
        }
        return tasks.remove(taskId);
    }
}
