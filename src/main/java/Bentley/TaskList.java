package Bentley;

import java.util.ArrayList;

/**
 * A class representing a list of tasks and providing methods to manage tasks.
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

    /**
     * Lists all tasks in the task list along with their indices.
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Adds a Todo task to the task list based on user input.
     *
     * @param userInput The user input command for adding a Todo task.
     * @throws IllegalArgumentException If the user input is incomplete.
     */
    public void addTodoTask(String userInput) {
        // Implementation
    }

    /**
     * Adds a Deadline task to the task list based on user input.
     *
     * @param userInput The user input command for adding a Deadline task.
     * @throws IllegalArgumentException If the user input is incomplete.
     */
    public void addDeadlineTask(String userInput) {
        // Implementation
    }

    /**
     * Adds an Event task to the task list based on user input.
     *
     * @param userInput The user input command for adding an Event task.
     * @throws IllegalArgumentException If the user input is incomplete.
     */
    public void addEventTask(String userInput) {
        // Implementation
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input command for marking a task as done.
     */
    public void markAsDone(String userInput) {
        // Implementation
    }

    /**
     * Marks a task as not done based on user input.
     *
     * @param userInput The user input command for marking a task as not done.
     */
    public void markAsUndone(String userInput) {
        // Implementation
    }

    /**
     * Deletes a task from the task list based on user input.
     *
     * @param userInput The user input command for deleting a task.
     */
    public void deleteTask(String userInput) {
        // Implementation
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
