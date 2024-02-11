package duke;

import java.util.ArrayList;

/**
 * This class represents a list of tasks.
 * It provides methods to create, list, add, mark, unmark, and delete tasks.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Creates a new empty task list.
     */
    public static void create() {
        taskList = new ArrayList<>();
    }

    /**
     * Lists the tasks in the task list.
     *
     * @return A string representation of the tasks in the list.
     */
    public static String list() {
        StringBuilder result = new StringBuilder();

        if (taskList.isEmpty()) {
            result.append("You have no tasks! Hooray!!!!!!!!!!");
        } else {
            result.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                int number = i + 1;
                result.append(number).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public static int listSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the list and returns a message string.
     *
     * @param task the task to add
     * @return A message indicating the task addition.
     */
    public static String addTask(Task task) {
        taskList.add(task);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task, taskList.size());
    }

    /**
     * Adds a task to the list without returning any message.
     *
     * @param task the task to add
     */
    public static void addTaskSilently(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task as done and returns a message string.
     *
     * @param idx the 1-based index of the task to mark
     * @return A message indicating the task marked as done.
     */
    public static String markTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.done();
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }

    /**
     * Marks a task as done without returning any message.
     *
     * @param idx the 1-based index of the task to mark
     */
    public static void markTaskSilently(int idx) {
        Task task = taskList.get(idx - 1);
        task.done();
    }

    /**
     * Marks a task as not done and returns a message string.
     *
     * @param idx the 1-based index of the task to unmark
     * @return A message indicating the task marked as not done yet.
     */
    public static String unmarkTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.undone();
        return String.format("OK, I've marked this task as not done yet:\n%s", task);
    }

    /**
     * Deletes a task from the list and returns a message string.
     *
     * @param idx the 1-based index of the task to delete
     * @return A message indicating the task deletion.
     */
    public static String deleteTask(int idx) {
        Task task = taskList.get(idx - 1);
        taskList.remove(idx - 1);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task, taskList.size());
    }

    /**
     * Returns the task at the specified index.
     *
     * @param idx the 1-based index of the task to return
     * @return the task at the specified index
     */
    public static Task getTask(int idx) {
        return taskList.get(idx - 1);
    }

    /**
     * Finds and returns a message string for tasks that match the provided search string.
     *
     * This method iterates through the task list, checking if each task's string representation
     * contains the provided search string (case-insensitive). Matching tasks are included in the
     * returned message string along with their corresponding index and task details.
     *
     * @param search The search string to match against task names.
     * @return A message string containing matching tasks or an indication of no matching tasks.
     */
    public static String findTask(String search) {
        StringBuilder result = new StringBuilder();
        boolean isThereMatch = false; // Flag to track if any matching tasks are found
        int count = 1; // Counter for displaying the index of matching tasks

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskName = task.toString2();

            // Check if the task name contains the search string (case-insensitive)
            if (taskName.toLowerCase().contains(search.toLowerCase())) {
                if (!isThereMatch) {
                    result.append("Here are the matching tasks in your list: \n");
                    isThereMatch = true;
                }
                result.append(count).append(". ").append(task).append("\n");
                count++;
            }
        }
        // Append a message if no matching tasks are found
        if (!isThereMatch) {
            result.append("No matching tasks found!");
        }

        return result.toString();
    }
}


