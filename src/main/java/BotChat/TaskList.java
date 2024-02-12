package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        // Assert that task is not null
        assert task != null : "Task should not be null";

        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
            return "Okay! Added to your list:\n" + task
                    + "\nNow you have " + tasks.size() + " tasks in your list.";
        } else {
            throw new BotChatException("Ohno :( Your task list is full. Complete some tasks first.");
        }
    }

    /**
     * Deletes a task from the task list based on the user input.
     *
     * @param input The user input containing the task index to be deleted.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the deletion process.
     */
    public String deleteTask(String input) throws BotChatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            // Assert that taskIndex is within valid range
            assert taskIndex >= 0 && taskIndex < tasks.size() : "Invalid task index";

            Task removedTask = tasks.remove(taskIndex);
            return "Okay. This task has been removed:\n" + removedTask
                    + "\nNow you have " + tasks.size() + " tasks in your list.";
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new BotChatException("Invalid task index inputted. Please try again.");
        }
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @return A response message containing the list of tasks or a message indicating an empty task list.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        } else {
            StringBuilder response = new StringBuilder("Here are your tasks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return response.toString();
        }
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param input The user input containing the task index to be marked as done.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the marking process.
     */
    public String markTask(String input) throws BotChatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).mark();
                return "Nice! This task has been marked as done:\n" + tasks.get(taskIndex);
            } else {
                throw new BotChatException("Invalid task index inputted. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BotChatException("Please indicate the task number you want to mark complete.");
        }
    }

    /**
     * Unmarks a task based on the user input.
     *
     * @param input The user input containing the task index to be unmarked.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the unmarking process.
     */
    public String unmarkTask(String input) throws BotChatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).unmark();
                return "Okay. This task has been unmarked:\n" + tasks.get(taskIndex);
            } else {
                throw new BotChatException("Invalid task index inputted. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BotChatException("Please indicate the task number you want to unmark.");
        }
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
        if (parts.length == 3) {
            String description = parts[0].substring(5);
            String from = parts[1].substring(5).trim();
            String to = parts[2].substring(3);

            if (!description.isEmpty()) {
                Event eventTask = createEventTask(description, from, to);
                return addTask(eventTask);
            } else {
                throw new BotChatException("Please provide a valid description of the task.");
            }
        } else {
            throw new BotChatException("Invalid format of Event task. Please try again with the correct format.\n"
                    + " event (event name) /from (start) /to (end)");
        }
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
            throw new BotChatException("Invalid date format. Please use yyyy-MM-dd or yyyy-MM-dd HHmm "
                    + "format for the event.");
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
        if (parts.length == 2) {
            String description = parts[0].substring(8);
            String by = parts[1].substring(3);

            if (!description.isEmpty()) {
                Deadline deadlineTask = createDeadlineTask(description, by);
                return addTask(deadlineTask);
            } else {
                throw new BotChatException("Please provide a valid description of the task.");
            }
        } else {
            throw new BotChatException("Invalid format of Deadline task. Please try again with the correct format.\n"
                    + "deadline (event name) /by (deadline)");
        }
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
        if (isValidDateFormat(by)) {
            return new Deadline(description, by);
        } else {
            throw new BotChatException("Invalid date format. Please use yyyy-mm-dd or yyyy-mm-dd HHmm "
                    + "format for the deadline.");
        }
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
                LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return true;
            } catch (DateTimeParseException ex) {
                return false;
            }
        }
    }

    /**
     * Adds a tod0 task to the task list based on the user input.
     *
     * @param input The user input containing the tod0 task details.
     * @return A response message or status.
     * @throws BotChatException If there's an error during the addition process.
     */
    public String addTodoTask(String input) throws BotChatException {
        if (!input.substring(4).isEmpty()) {
            Task task = new Todo(input.substring(4));
            return addTask(task);
        } else {
            throw new BotChatException("Please provide a valid description of the task.");
        }
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

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(input.substring(5).toLowerCase())) {
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
}
