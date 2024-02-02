package jayne.task;

import java.util.ArrayList;
import java.util.List;

import jayne.Storage;
import jayne.JayneException;

/**
 * Class with list of task
 */
public class TaskList {
    private final List<Task> taskArray;
    private int taskCount;

    private Storage storage;

    /**
     * Constructor for TaskList
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
     * Finds and displays tasks whose description contains the given keyword.
     *
     * @param keyword the keyword to search for in task descriptions.
     */
    public void findTask(String keyword) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(++count + "." + task);
            }
        }
        if (count == 0) {
            System.out.println("No tasks found with the keyword: " + keyword);
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Delete tasks
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
     * adds tasks
     */
    public void addTask(Task task) {
        taskArray.add(task);
        this.taskCount = taskCount + 1;
        storage.saveTasks(taskArray);
    }

    public Task getTask(int index) {
        if (index >= 0 && index <= taskArray.size()) {
            return taskArray.get(index - 1);
        }
        System.out.println("Index invalid");
        return null;
    }
    /**
     * Mark a task as done
     */
    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsDone();
            storage.saveTasks(taskArray);
        }
    }
    /**
     * Mark a task as not done
     */
    public void markTaskAsNotDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsNotDone();
            storage.saveTasks(taskArray);
        }
    }
    /**
     * prints a task
     */
    public void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskArray.size(); i++) {
            System.out.println((i + 1) + ". " + taskArray.get(i).toString());
        }
    }
}
