package jayne.task;

import java.util.ArrayList;
import java.util.List;

import jayne.JayneException;
import jayne.Storage;

/**
 * Represents a list of tasks. This class handles operations such as
 * adding, deleting, and marking tasks as done or not done.
 */
public class TaskList {
    private final List<Task> taskArray;
    private int taskCount;

    private Storage storage;

    /**
     * Constructs a new TaskList and initializes it with tasks loaded from storage.
     *
     * @param storage the Storage object used for loading and saving tasks.
     */
    public TaskList(Storage storage) {
        this.taskArray = new ArrayList<>();
        this.storage = storage;
        storage.renameFileIfExists();
        storage.loadTasks(taskArray);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Storage getStorage() {
        return storage;
    }
    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param taskNumber the position of the task in the task list.
     * @return the deleted task.
     * @throws JayneException if the task number is invalid.
     */
    public Task deleteTask(int taskNumber) throws JayneException {
        if (taskNumber < 1 || taskNumber > taskArray.size()) {
            throw new JayneException("Task number " + taskNumber + " does not exist.");
        }
        this.taskCount = taskCount - 1;
        storage.saveTasks(taskArray);
        return taskArray.remove(taskNumber - 1);
    }
    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        taskArray.add(task);
        this.taskCount = taskCount + 1;
        storage.saveTasks(taskArray);
    }
    /**
     * Retrieves the task at the specified position in the task list.
     *
     * @param index the position of the task in the task list.
     * @return the task at the specified position, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (index >= 0 && index <= taskArray.size()) {
            return taskArray.get(index - 1);
        }
        System.out.println("Index invalid");
        return null;
    }
    /**
     * Marks the task at the specified position in the task list as done and saves the updated list to storage.
     *
     * @param taskNumber the position of the task in the task list.
     */
    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsDone();
            storage.saveTasks(taskArray);
        }
    }
    /**
     * Marks the task at the specified position in the task list as not done and saves the updated list to storage.
     *
     * @param taskNumber the position of the task in the task list.
     */
    public void markTaskAsNotDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsNotDone();
            storage.saveTasks(taskArray);
        }
    }
    /**
     * Displays all tasks in the task list to the user.
     */
    public void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println((i + 1) + ". " + taskArray.get(i).toString());
        }
    }
}
