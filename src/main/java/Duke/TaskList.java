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
     * Prints all tasks in the list to the console.
     */
    public static void list() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks! Hooray!!!!!!!!!!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int number = i + 1;
                System.out.println(number + ". " + taskList.get(i));
            }
        }
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
     * Adds a task to the list and prints a message to the console.
     *
     * @param task the task to add
     */
    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static void addTaskSilent(Task task) {
        taskList.add(task);
    }

    public static void markTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Marks a task as done without printing any message.
     *
     * @param idx the 1-based index of the task to mark
     */
    public static void markTaskSilent(int idx) {
        Task task = taskList.get(idx - 1);
        task.done();
    }

    /**
     * Marks a task as not done and prints a message to the console.
     *
     * @param idx the 1-based index of the task to unmark
     */
    public static void unmarkTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.undone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Deletes a task from the list and prints a message to the console.
     *
     * @param idx the 1-based index of the task to delete
     */
    public static void deleteTask(int idx) {
        Task task = taskList.get(idx - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size() - 1));
        taskList.remove(idx - 1);
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
     * Finds and displays tasks in the task list that match the provided search string.
     *
     * This method iterates through the task list, checking if each task's string representation
     * contains the provided search string (case-insensitive). Matching tasks are displayed with
     * their corresponding index and task details. If no matches are found, an appropriate message
     * is printed to indicate no matching tasks.
     *
     * @param search The search string to match against task names.
     */
    public static void findTask(String search) {
        boolean isThereMatch = false; // Flag to track if any matching tasks are found
        int number = 1; // Counter for displaying the index of matching tasks

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String taskName = task.toString2();

            // Check if the task name contains the search string (case-insensitive)
            if (taskName.toLowerCase().contains(search.toLowerCase())) {
                if (!isThereMatch) {
                    System.out.println("Here are the matching tasks in your list: ");
                    isThereMatch = true;
                }
                System.out.println(number + ". " + task);
                number++;
            }
        }
    
        // Print a message if no matching tasks are found
        if (!isThereMatch) {
            System.out.println("No matching tasks found!");
        }
    }

}

