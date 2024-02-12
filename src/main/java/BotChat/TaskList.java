package BotChat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a collection of tasks in the botChat application.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return A response message or status.
     * @throws BotChatException If the task list is full or if there's an error during the addition process.
     */
    public String addTask(Task task) throws BotChatException {
        if (task == null) {
            throw new BotChatException("Task cannot be null.");
        }

        if (tasks.size() >= MAX_TASKS) {
            throw new BotChatException("Your task list is full. Complete some tasks first.");
        }

        tasks.add(task);
        return "Okay! Added to your list:\n" + task + "\nNow you have " + tasks.size() + " tasks in your list.";
    }

    /**
     * Deletes a task from the task list based on the user input.
     *
     * @param input The user input containing the task index to be deleted.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the deletion process.
     */
    public String deleteTask(String input) throws BotChatException {
        int taskIndex = getTaskIndex(input.substring(7));
        Task removedTask = tasks.remove(taskIndex);
        return "Okay. This task has been removed:\n" + removedTask + "\nNow you have "
                + tasks.size() + " tasks in your list.";
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @return A response message containing the list of tasks or a message indicating an empty task list.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }

        StringBuilder response = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param input The user input containing the task index to be marked as done.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the marking process.
     */
    public String markTask(String input) throws BotChatException {
        int taskIndex = getTaskIndex(input.substring(5));
        tasks.get(taskIndex).mark();
        return "Nice! This task has been marked as done:\n" + tasks.get(taskIndex);
    }

    /**
     * Unmarks a task based on the user input.
     *
     * @param input The user input containing the task index to be unmarked.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the unmarking process.
     */
    public String unmarkTask(String input) throws BotChatException {
        int taskIndex = getTaskIndex(input.substring(7));
        tasks.get(taskIndex).unmark();
        return "Okay. This task has been unmarked:\n" + tasks.get(taskIndex);
    }

    /**
     * Adds an event task to the task list based on the user input.
     *
     * @param input The user input containing the event task details.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the addition process.
     */
    public String addEventTask(String input) throws BotChatException {
        String[] parts = input.split("/", 3);
        if (parts.length != 3) {
            throw new BotChatException("Invalid format of Event task. Please try again with the correct format.\n"
                    + " event (event name) /from (start) /to (end)");
        }

        String description = parts[0].substring(5);
        String from = parts[1].substring(5).trim();
        String to = parts[2].substring(3);

        if (description.isEmpty()) {
            throw new BotChatException("Please provide a valid description of the task.");
        }

        Event eventTask = createEventTask(description, from, to);
        return addTask(eventTask);
    }

    /**
     * Creates an event task based on the provided details.
     *
     * @param description The description of the event task.
     * @param from        The starting date/time of the event.
     * @param to          The ending date/time of the event.
     * @return The created Event task.
     * @throws BotChatException If there's an error during the creation process.
     */
    private Event createEventTask(String description, String from, String to) throws BotChatException {
        try {
            return new Event(description, from, to);
        } catch (Exception e) {
            throw new BotChatException("Invalid date format. Please use yyyy-MM-dd or "
                    + "yyyy-MM-dd HHmm format for the event.");
        }
    }

    /**
     * Adds a deadline task to the task list based on the user input.
     *
     * @param input The user input containing the deadline task details.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the addition process.
     */
    public String addDeadlineTask(String input) throws BotChatException {
        String[] parts = input.split("/", 2);
        if (parts.length != 2) {
            throw new BotChatException("Invalid format of Deadline task. Please try again with the correct format.\n"
                    + "deadline (event name) /by (deadline)");
        }

        String description = parts[0].substring(8);
        String by = parts[1].substring(3);

        if (description.isEmpty()) {
            throw new BotChatException("Please provide a valid description of the task.");
        }

        Deadline deadlineTask = createDeadlineTask(description, by);
        return addTask(deadlineTask);
    }

    /**
     * Creates a deadline task based on the provided details.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date/time.
     * @return The created Deadline task.
     * @throws BotChatException If there's an error during the creation process.
     */
    private Deadline createDeadlineTask(String description, String by) throws BotChatException {
        if (!isValidDateFormat(by)) {
            throw new BotChatException("Invalid date format. Please use yyyy-MM-dd or "
                    + "yyyy-MM-dd HHmm format for the deadline.");
        }

        return new Deadline(description, by);
    }

    /**
     * Checks if the provided date string has a valid date format.
     *
     * @param by The date string to be checked.
     * @return True if the date format is valid, false otherwise.
     */
    private boolean isValidDateFormat(String by) {
        try {
            LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            try {
                LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return true;
            } catch (DateTimeParseException ex) {
                return false;
            }
        }
    }

    /**
     * Adds a todo task to the task list based on the user input.
     *
     * @param input The user input containing the todo task details.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the addition process.
     */
    public String addTodoTask(String input) throws BotChatException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new BotChatException("Please provide a valid description of the task.");
        }

        Task task = new Todo(description);
        return addTask(task);
    }

    /**
     * Finds tasks in the task list that contain the specified keyword in their description.
     *
     * @param input The keyword to search for in task descriptions.
     * @return A response message containing the matching tasks.
     */
    public String findTasks(String input) {
        StringBuilder response = new StringBuilder();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        String keyword = input.substring(5).toLowerCase();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            response.append("No matching tasks found.");
        } else {
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        return response.toString();
    }

    private int getTaskIndex(String input) throws BotChatException {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new BotChatException("Invalid task index inputted. Please try again.");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new BotChatException("Invalid task index inputted. Please try again.");
        }
    }
}
