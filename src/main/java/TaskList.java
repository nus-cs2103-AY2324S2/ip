import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TaskList {

    private ArrayList<Task> taskStore;

    public TaskList() {
        this.taskStore = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> loadedTaskStore) {
        this.taskStore = loadedTaskStore;
    }

    public Task markTask(int index) {
        Task currTask = taskStore.get(index);
        currTask.updateTask(true);
        return currTask;
    }

    protected Task unmarkTask(int index) {
        Task currTask = taskStore.get(index);
        currTask.updateTask(false);
        return currTask;
    }

    protected Task deleteTask(int index) {
        Task deletedTask = taskStore.remove(index);
        return deletedTask;
    }
    protected void addTask(Task taskToBeAdded) {
        taskStore.add(taskToBeAdded);
    }
    protected ArrayList<Task> getTaskStore() {
        return taskStore;
    }

    protected int listSize() {
        return taskStore.size();
    }
}
