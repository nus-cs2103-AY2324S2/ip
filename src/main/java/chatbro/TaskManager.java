package chatbro;

import java.util.ArrayList;

/**
 * TaskManager class that manages task list and task count.
 */
public class TaskManager {
    private static ArrayList<Task> taskList;
    private static int taskCount = 0;

    public TaskManager() {
        taskList = new ArrayList<>(101); // Index 0 left empty for 1-based indexing
        taskList.add(null); // First element left empty for 1-based indexing
    }
    /**
     * Deletes a task from the list.
     * @param index Index of the task to be deleted.
     */
    public static void deleteTask(int index) { // Index is guaranteed to contain a valid task
        if (index < 1 || index > 100) {
            Ui.printMessage("You can only delete tasks within the range of 1 to 100.");
            return;
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        decrementTaskCount();
    }
    /**
     * Adds a task to the list.
     * @param task Task to be added.
     */
    public static void addTask(Task task) {
        for (int i = 1; i <= 100; i++) {
            if (taskList.get(i) == null) {
                taskList.add(i, task);
                incrementTaskCount();
                return;
            }
        }
        Ui.printMessage("Sorry bro, you can only have up to 100 tasks in your list.");
    }
    public static Task getTask(int index) {
        return taskList.get(index);
    }
    public static ArrayList<Task> getList() {
        return taskList;
    }
    /**
     * Increases the task count by 1.
     */
    public static void incrementTaskCount() {
        taskCount++;
    }

    /**
     * Decreases the task count by 1.
     */
    public static void decrementTaskCount() {
        taskCount--;
    }

    /**
     * Returns the task count.
     * @return ChatBro.Task count.
     */
    public static int getTaskCount() {
        return taskCount;
    }
}
