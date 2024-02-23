package bentley;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Bentley task management application.
 * Tasks can include Todo, Deadline, and Event types.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Displays the list of tasks with their respective indices.
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Adds a Todo task to the list based on user input.
     *
     * @param userInput The user input containing the task description.
     * @throws IllegalArgumentException If the description is missing.
     */
    public void addTodoTask(String userInput) {
        if (userInput.length() <= 5) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("looks like the description is missing");
        }
        tasks.add(new Todo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a Deadline task to the list based on user input.
     *
     * @param userInput The user input containing the task description and deadline.
     * @throws IllegalArgumentException If the description or deadline is missing.
     */
    public void addDeadlineTask(String userInput) {
        if (userInput.length() <= 9) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        String[] parts = userInput.substring(9).split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        tasks.add(new Deadline(description, by));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds an Event task to the list based on user input.
     *
     * @param userInput The user input containing the task description and event
     *                  dates.
     * @throws IllegalArgumentException If the description or event dates are
     *                                  missing.
     */
    public void addEventTask(String userInput) {
        if (userInput.length() <= 6) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        String[] parts = userInput.substring(6).split("/from");
        String description = parts[0].trim();
        String[] eventParts = parts[1].trim().split("/to");
        String from = eventParts[0].trim();
        String to = eventParts[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new IllegalArgumentException(
                    "looks like something is missing (description/ start date/ end date)");
        }
        tasks.add(new Event(description, from, to));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input containing the task index to mark as done.
     */
    public void markAsDone(String userInput) {
        System.out.println(" Nice! I've marked this task as done:");
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        tasks.get(taskNumber - 1).markAsDone();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Marks a task as not done yet based on user input.
     *
     * @param userInput The user input containing the task index to mark as not done
     *                  yet.
     */
    public void markAsUndone(String userInput) {
        System.out.println(" OK, I've marked this task as not done yet:");
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        tasks.get(taskNumber - 1).markAsUndone();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Deletes a task from the list based on user input.
     *
     * @param userInput The user input containing the task index to delete.
     */
    public void deleteTask(String userInput) {

        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    /**
 * Finds tasks containing a given keyword and displays them.
 *
 * @param keyword The keyword to search for in task descriptions.
 */
public void findTasks(String keyword) {
    System.out.println("Here are the matching tasks in your list:");

    int count = 0;
    for (int i = 0; i < tasks.size(); i++) {
        Task currentTask = tasks.get(i);
        if (currentTask.getDescription().contains(keyword)) {
            System.out.println((i + 1) + ". " + currentTask);
            count++;
        }
    }

    if (count == 0) {
        System.out.println("No matching tasks found.");
    }
}

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}