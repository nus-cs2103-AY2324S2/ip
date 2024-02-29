package bentley;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Bentley task management application.
 * Tasks can include Todo, Deadline, and Event types.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the provided list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list and returns a message indicating the result.
     *
     * @param t The task to be added.
     * @return A string message indicating the result of adding the task.
     */
    public String addToList(Task t) {
        this.tasks.add(t);
        return "Got it. I've added this task:\n" + t.toString() + "\nNow you have " + this.tasks.size()
                + " tasks in the list.";
    }

    /**
     * Generates a formatted string listing all tasks in the TaskList.
     *
     * @return A formatted string listing all tasks in the TaskList.
     */
    public String listTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }

     /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input containing the task index to mark as done.
     * @return A string indicating the result of marking the task as done.
     */
    public String markAsDone(String userInput) {
        StringBuilder result = new StringBuilder("Nice! I've marked this task as done:\n");

        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();

            for (int i = 0; i < tasks.size(); i++) {
                result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }

            return result.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid input gracefully
            return "There is no such task number. Please provide a valid task number.";
        }
    }

    /**
     * Marks a task as not done yet based on user input.
     *
     * @param userInput The user input containing the task index to mark as not done yet.
     * @return A string indicating the result of marking the task as not done yet.
     */
    public String markAsUndone(String userInput) {
        StringBuilder result = new StringBuilder("OK, I've marked this task as not done yet:\n");

        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            Task task = tasks.get(taskNumber - 1);
            task.markAsUndone();

            for (int i = 0; i < tasks.size(); i++) {
                result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }

            return result.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid input gracefully
            return "There is no such task number. Please provide a valid task number.";
        }
    }

    /**
     * Deletes a task from the list based on user input.
     *
     * @param userInput The user input containing the task index to delete.
     * @return A string indicating the result of deleting the task.
     */
    public String deleteTask(String userInput) {
        StringBuilder result = new StringBuilder();

        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);

            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task removedTask = tasks.remove(taskNumber - 1);
                result.append("Noted. I've removed this task:\n")
                        .append("  ").append(removedTask).append("\n")
                        .append("Now you have ").append(tasks.size()).append(" tasks in the list.");
            } else {
                result.append("Invalid task index. Please provide a valid index.");
            }

            return result.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "There is no such task number. Please provide a valid task number.";
        }
    }

     /**
     * Finds tasks containing a given keyword and displays them.
     *
     * @param userInput The user input containing the keyword to search for.
     * @return A string containing the matching tasks or a message if no tasks match the keyword.
     */
    public String findTasks(String userInput) {

        String[] parts = userInput.split(" ", 2); // Split into two parts, limit to 2 parts
        String keyword = parts.length > 1 ? parts[1] : "";

        StringBuilder result = new StringBuilder();

        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                if (count == 0) {
                    result.append("Here are the matching tasks in your list:\n");
                }
                result.append((i + 1)).append(". ").append(currentTask).append("\n");
                count++;
            }
        }

        if (count == 0) {
            result.append("No matching tasks found.");
        }

        return result.toString();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {

        return this.tasks;
    }
}