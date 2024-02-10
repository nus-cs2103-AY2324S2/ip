package whisper;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) throws WhisperException {
        checkIndex(index);
        //taskList.remove(index);
        Task removedTask = taskList.remove(index);
        System.out.println("Task deleted: " + removedTask.getDescription());
    }

    public void markTaskAsDone(int index) throws WhisperException {
        checkIndex(index);
        taskList.get(index).markAsDone();
    }

    public void markTaskAsUndone(int index) throws WhisperException {
        checkIndex(index);
        taskList.get(index).markAsUndone();
    }

    public ArrayList<Task> getTaskList() {
        // Return a copy to avoid direct manipulation of the internal list
        return new ArrayList<>(taskList);
    }

    public Task getTask(int index) throws WhisperException {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new WhisperException("Invalid task number.");
        }
    }

    private void checkIndex(int index) throws WhisperException {
        if (index < 0 || index >= taskList.size()) {
            throw new WhisperException("Invalid task number.");
        }
    }
}
