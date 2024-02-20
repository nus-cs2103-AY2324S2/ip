package duke;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private static Task[] tasks;
    private static int taskNum;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        tasks = new Task[100];
        taskNum = 0;
    }

    /**
     * Constructor for TaskList.
     * @param loadTasks The tasks to be loaded.
     */
    public TaskList(Task[] loadTasks) {
        tasks = new Task[100];
        taskNum = 0;

        for (int i = 0; i < loadTasks.length; i++) {
            if (loadTasks[i] != null) {
                tasks[i] = loadTasks[i];
                taskNum++;
            }
        }
    }

    /**
     * Returns the number of tasks.
     * @return The number of tasks.
     */
    public static int getTaskNum() {
        return taskNum;
    }

    /**
     * Returns the tasks.
     * @return The tasks.
     */
    public static Task[] getTasks() {
        return tasks;
    }

    /**
     * Returns the task at the specified index.
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public static Task getTask(int index) {
        assert index >= 0 && index < taskNum : "Invalid task index";
        return tasks[index];
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (taskNum < tasks.length) {
            tasks[taskNum] = task;
            taskNum++;
        }
    }

    /**
     * Removes a task from the list of tasks.
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        assert index >= 0 && index < taskNum : "Invalid task index";
        for (int i = index; i < taskNum - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        tasks[taskNum - 1] = null;
        taskNum--;
    }

    /**
     * Returns the string representation of the list of tasks.
     */
    public void markTaskAsDone(int index) {
        tasks[index].markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     * @param index The index of the task to be marked as not done.
     */
    public void markTaskAsNotDone(int index) {
        tasks[index].markAsNotDone();
    }

    public static boolean containsTask(String taskStr) {
        for (int i = 0; i < taskNum; i++) {
            if (tasks[i] != null && String.valueOf(tasks[i]).equals(taskStr)) {
                return true;
            }
        }
        return false;
    }
}
