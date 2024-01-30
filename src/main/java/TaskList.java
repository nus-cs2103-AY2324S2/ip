import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void unmarkTaskAsDone(int index) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void deleteTask(int taskIndex, Ui ui) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            ui.showTaskDeleted(removedTask, tasks.size());
        } else {
            ui.showInvalidTaskIndex();
        }
    }

    // Add other methods as needed
}
