package cookie.task;

import java.util.Objects;

/**
 * Represents a list of tasks in the Cookie application.
 */
public class TaskList {
    private Task[] tasks;
    private int counter;

    /**
     * Constructs a TaskList object with an initial capacity of 100 tasks.
     * Initializes the counter to 0.
     */
    public TaskList() {
        tasks = new Task[100]; // Or any initial size you prefer
        counter = 0;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks[counter] = task;
        counter++;
    }

    /**
     * Deletes a task from the task list based on its task number.
     *
     * @param taskNumber The task number of the task to be deleted.
     * @throws IllegalArgumentException If the task number is invalid.
     */
    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > counter) {
            throw new IllegalArgumentException("Invalid task number");
        }

        for (int i = taskNumber - 1; i < counter - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[counter - 1] = null;
        counter--;
    }

    /**
     * Marks a task as done based on its task number.
     *
     * @param taskNumber The task number of the task to be marked as done.
     * @throws IllegalArgumentException If the task number is invalid.
     */
    public void markTaskAsDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > counter) {
            throw new IllegalArgumentException("Invalid task number");
        }

        tasks[taskNumber - 1].markAsDone();
    }

    public Task[] searchKeyWord(String keyword) {
        int count = 0;
        Task[] containsKeywords = new Task[100];

        for (int i = 0; i < counter; i++) {
            String taskDesc = tasks[i].description;
            if (taskDesc.contains(keyword)) {
                containsKeywords[count] = tasks[i];
                count++;
            }
        }

        return containsKeywords;
    }

    public Task[] displayTagged(String tag) {
        int count = 0;
        Task[] tagged = new Task[100];

        for (int i = 0; i < counter; i++) {
            String taskTag = tasks[i].tag;
            if (Objects.equals(taskTag, tag)) {
                tagged[count] = tasks[i];
                count++;
            }
        }

        return tagged;
    }

    /**
     * Retrieves tasks stored in the task list.
     *
     * @return An array of tasks.
     */
    public Task[] getTasks() {
        return tasks;
    }

    /**
     * Retrieves the current count of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getCounter() {
        return counter;
    }
}
