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
    public TaskList(Storage storage) throws JayneException {
        this.taskArray = new ArrayList<>();
        this.storage = storage;
        assert storage != null : "Storage should not be null";
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
     * Finds and displays tasks whose description contains the given keyword.
     *
     * @param keyword the keyword to search for in task descriptions.
     */
    public String findTask(String keyword) {
        int count = 0;
        String output = "";
        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                output = ++count + "." + task;
            }
        }
        if (count == 0) {
            output = "No tasks found with the keyword: " + keyword;
        }

        return "Here are the matching tasks in your list:\n" + output;
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
    public void addTask(Task task) throws JayneException {
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
    public void markTaskAsDone(int taskNumber) throws JayneException {
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
    public void markTaskAsNotDone(int taskNumber) throws JayneException {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsNotDone();
            storage.saveTasks(taskArray);
        }
    }
    /**
     * Displays all tasks in the task list to the user.
     */
    public String display() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskArray.size(); i++) {
            output.append(i + 1).append(". ").append(taskArray.get(i).toString()).append("\n");
        }

        return "Here are the tasks in your list:\n" + output;
    }
}
