/*
 * Package: Echo
 * Module/Project Name: Echo
 * File: TaskManager.java
 *
 * Description:
 * This class manages tasks, handling operations such as adding, listing, marking, unmarking, and deleting tasks.
 * It interacts with the Storage class to save and load tasks from a file.
 *
 */

package echo;

import echo.task.Task;
import echo.task.Todo;
import echo.task.Deadline;
import echo.task.Event;
import echo.storage.Storage;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Platform;

public class TaskManager {
    private List<Task> tasks;
    private final String FILE_PATH = "." + File.separator
            + "data" + File.separator + "echo.txt";
    private Storage storage;

    private echo.Echo echo;

    /**
     * Constructor for the TaskManager class.
     *
     * @param storage The Storage object to handle file operations.
     * @param echo    The Echo instance for communication.
     */
    public TaskManager(Storage storage, Echo echo) {
        assert storage != null : "Storage cannot be null";
        assert echo != null : "Echo instance cannot be null";
        this.tasks = new ArrayList<>();
        this.storage = storage;
        this.echo = echo;
        loadTasksFromFile();
    }

    /**
     * Saves tasks to the file using the Storage class.
     */
    private void saveTasksToFile() {
        storage.save(tasks);
    }

    /**
     * Loads tasks from the file using the Storage class.
     */
    private void loadTasksFromFile() {
        tasks = storage.load();
    }

    /**
     * Executes a command based on user input.
     *
     * @param command The user input command.
     */
    public void executeCommand(String command) {
        assert command != null : "Command cannot be null";
        String[] tokens = command.split(" ", 2);

        switch (tokens[0].toLowerCase()) {
        case "list":
            listTasks();
            break;
        case "mark":
            markTask(tokens);
            break;
        case "unmark":
            unmarkTask(tokens);
            break;
        case "delete":
            deleteTask(tokens);
            break;
        case "find":
            if (tokens.length == 2) {
                findTasks(tokens[1]);
            } else {
                System.out.println("Invalid command. Usage: find <keyword>");
            }
            break;
        case "bye":
            break;
        default:
            addTask(tokens);
        }
    }

    /**
     * Lists all tasks in GUI.
     */

    public void listTasks() {
        StringBuilder response = new StringBuilder("OK Here are the tasks in your list:\n");

        if (tasks.isEmpty()) {
            response.append("No tasks in the list.\n");
        } else {
            response.append(tasks.stream()
                    .map(task -> tasks.indexOf(task) + 1 + ". " + task.toString())
                    .collect(Collectors.joining("\n")));
            response.append("\n");
        }
        response.append("You really can't remember them yourself?");
        echo.displayBotResponse(response.toString());
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param tokens The user input tokens.
     */
    public void markTask(String[] tokens) {
        assert tokens != null : "Tokens cannot be null";
        if (tokens.length == 2) {
            int index = Integer.parseInt(tokens[1]);
            if (isValidIndex(index)) {
                tasks.get(index - 1).markAsDone();
                echo.displayBotResponse("Can't believe you finished a task haha: "
                        + tasks.get(index - 1));
            } else {
                echo.displayBotResponse("Invalid task index.");
            }
        } else {
            echo.displayBotResponse("Invalid command. Usage: mark <index>");
        }
        saveTasksToFile();
    }

    /**
     * Marks a task as undone based on the user input.
     *
     * @param tokens The user input tokens.
     */
    public void unmarkTask(String[] tokens) {
        assert tokens != null : "Tokens cannot be null";
        if (tokens.length == 2) {
            int index = Integer.parseInt(tokens[1]);
            if (isValidIndex(index)) {
                tasks.get(index - 1).markAsUndone();
                echo.displayBotResponse("I knew you couldn't do this task: "
                        + tasks.get(index - 1));
            } else {
                echo.displayBotResponse("Invalid task index.");
            }
        } else {
            echo.displayBotResponse("Invalid command. Usage: unmark <index>");
        }
        saveTasksToFile();
    }

    /**
     * Adds a task to the task list without performing a scheduling conflict check. Displays a confirmation message and
     * saves the tasks to the file.
     *
     * @param newTask The task to be added.
     */
    private void addTaskWithoutConflictCheck(Task newTask) {
        tasks.add(newTask);
        echo.displayBotResponse(getTaskAddedMessage(tasks.size()));
        saveTasksToFile();
    }

    /**
     * Creates a new {@link Deadline} task based on the provided task description.
     *
     * @param taskDescription The description of the deadline task.
     * @return A new {@link Deadline} task.
     * @throws IllegalArgumentException If the task description does not match the expected format.
     */
    private Deadline createDeadline(String taskDescription) {
        String[] deadlineTokens = taskDescription.split(" /by ", 2);
        if (deadlineTokens.length != 2) {
            throw new IllegalArgumentException("NO! Invalid command. " +
                    "Enter: deadline <description> /by <date/time>");
        }
        return new Deadline(deadlineTokens[0], deadlineTokens[1]);
    }

    /**
     * Creates a new {@link Event} task based on the provided task description.
     *
     * @param taskDescription The description of the event task.
     * @return A new {@link Event} task.
     * @throws IllegalArgumentException If the task description does not match the expected format.
     */
    private Event createEvent(String taskDescription) {
        String[] eventTokens = taskDescription.split(" /from ", 2);
        if (eventTokens.length != 2) {
            throw new IllegalArgumentException("NO! Invalid command. " +
                    "Enter: event <description> /from <start> /to <end>");
        }
        String[] toTokens = eventTokens[1].split(" /to ", 2);
        if (toTokens.length != 2) {
            throw new IllegalArgumentException("NO! Invalid command. " +
                    "Enter: event <description> /from <start> /to <end>");
        }
        return new Event(eventTokens[0], toTokens[0], toTokens[1]);
    }

    /**
     * Adds a task based on the provided command tokens. Supports "todo," "deadline," and "event" tasks.
     * Displays appropriate error messages for invalid commands or task descriptions.
     *
     * @param tokens The command tokens containing the task type and description.
     */
    public void addTask(String[] tokens) {
        assert tokens != null : "Tokens cannot be null";
        try {
            if (tokens.length != 2) {
                throw new IllegalArgumentException("NO! I don't know what is this! " +
                        "Invalid command. Supported tasks: todo, deadline, event");
            }
            String[] taskTokens = tokens;

            String taskType = taskTokens[0].toLowerCase();
            if (taskTokens[1].isEmpty()) {
                throw new IllegalArgumentException("NO! " +
                        "The description of a task cannot be empty.");
            }
            String taskDescription = taskTokens[1].trim();

            Task newTask;
            switch (taskType) {
            case "todo":
                if (taskDescription.isEmpty()) {
                    throw new IllegalArgumentException("NO! " +
                            "The description of a todo cannot be empty.");
                }
                newTask = new Todo(taskDescription);
                break;
            case "deadline":
                newTask = createDeadline(taskDescription);
                break;
            case "event":
                newTask = createEvent(taskDescription);
                break;
            default:
                throw new IllegalArgumentException("No! I don't know what is this! " +
                        "Invalid task type. Supported types: todo, deadline, event");
            }

            if (!hasSchedulingConflict(newTask)) {
                addTaskWithoutConflictCheck(newTask);
            } else {
                echo.displayBotResponse("You already have a task at this time dumb!");
            }
        } catch (IllegalArgumentException e) {
            echo.displayBotResponse(e.getMessage());
        }
    }

    /**
     * Checks if the given index is valid for the tasks list.
     *
     * @param index The index to check.
     * @return true if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    /**
     * Prints a message indicating the successful addition of a task.
     *
     * @param size The size of the tasks list after the addition.
     * @return string response
     */
    private String getTaskAddedMessage(int size) {
        return "Ok you are never going to finish your tasks: :\n" +
                "  " + tasks.get(size - 1) + "\n" +
                "Now you have " + size + " tasks in the list.\n" ;
    }

    /**
     * Prints a message indicating the successful deletion of a task.
     *
     * @param removedTask The task removed.
     * @return string response
     */
    private String getTaskDeletedMessage(Task removedTask) {
        return "Did you finish it or did you just avoid it?\n" + "Removed task" +
                "  " + removedTask + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.\n" ;
    }

    /**
     * Deletes a task based on the user input.
     *
     * @param tokens The user input tokens.
     */
    public void deleteTask(String[] tokens) {
        try {
            if (tokens.length != 2) {
                throw new IllegalArgumentException("NO! Invalid command. " +
                        "Enter: delete <task number>");
            }

            int taskNumber = Integer.parseInt(tokens[1]) - 1;  // Adjusting for 0-based index
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new IllegalArgumentException("NO!" +
                        "Task number does not exist. Enter a valid task number to delete.");
            }

            Task removedTask = tasks.remove(taskNumber);
            echo.displayBotResponse(getTaskDeletedMessage(removedTask));
        } catch (NumberFormatException e) {
            echo.displayBotResponse("NO! Invalid task number. " +
                    "Enter a valid task number to delete.");
        } catch (IllegalArgumentException e) {
            echo.displayBotResponse(e.getMessage());
        }
        saveTasksToFile();
    }

    /**
     * Deletes all tasks and resets the file.
     */
    public void deleteAllTasks() {
        tasks.removeAll(tasks);
        resetFile();
    }

    /**
     * Resets the file by clearing its content.
     */
    public void resetFile() {
        try {
            File file = new File(FILE_PATH);

            // Create a new file writer with append mode set to false (clears existing content)
            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(file, false))) {
                // Clear the tasks list
                tasks.clear();
                System.out.println("Tasks list cleared.");

                System.out.println("File content cleared successfully.");
            }

        } catch (IOException e) {
            System.out.println("Error resetting file: " + e.getMessage());
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks containing the specified keyword in their descriptions.
     * Case-insensitive matching is performed.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findTasks(String keyword) {
        assert keyword != null : "Keyword cannot be null";
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        
        StringBuilder response = new StringBuilder();

        if (matchingTasks.isEmpty()) {
            response.append("Looks like you lost your memory. No matching tasks found.\n");
        } else {
            response.append("OK here are the matching tasks in your list:\n");
            response.append(matchingTasks.stream()
                    .map(task -> matchingTasks.indexOf(task) + 1 + ". " + task.toString())
                    .collect(Collectors.joining("\n")));
            response.append("\n");
        }

        echo.displayBotResponse(response.toString());
    }


    /**
     * Checks if adding a new task would result in a scheduling conflict with existing tasks.
     *
     * @param newTask The new task to be checked for conflicts.
     * @return {@code true} if there is a scheduling conflict, {@code false} otherwise.
     */
    public boolean hasSchedulingConflict(Task newTask) {
        if (newTask instanceof Event) {
            return hasEventConflict((Event) newTask);
        } else if (newTask instanceof Deadline) {
            return hasDeadlineConflict((Deadline) newTask);
        }
        return false; // No conflict for other task types
    }

    /**
     * Checks if adding a new event would result in a scheduling conflict with existing events.
     *
     * @param newEvent The new event to be checked for conflicts.
     * @return {@code true} if there is a scheduling conflict, {@code false} otherwise.
     */
    private boolean hasEventConflict(Event newEvent) {
        for (Task task : tasks) {
            if (task instanceof Event) {
                Event existingEvent = (Event) task;
                if (isTimeRangeOverlapping(newEvent, existingEvent)) {
                    return true; // Clash detected
                }
            }
        }
        return false; // No clash
    }

    /**
     * Checks if adding a new deadline would result in a scheduling conflict with existing deadlines.
     *
     * @param newDeadline The new deadline to be checked for conflicts.
     * @return {@code true} if there is a scheduling conflict, {@code false} otherwise.
     */
    private boolean hasDeadlineConflict(Deadline newDeadline) {
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline existingDeadline = (Deadline) task;
                if (isDateTimeEqual(newDeadline.getBy(), existingDeadline.getBy())) {
                    return true; // Clash detected
                }
            }
        }
        return false; // No clash
    }

    /**
     * Checks if two date-time values are equal.
     *
     * @param dateTime1 The first date-time value.
     * @param dateTime2 The second date-time value.
     * @return {@code true} if the date-time values are equal, {@code false} otherwise.
     */
    private boolean isDateTimeEqual(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isEqual(dateTime2);
    }

    /**
     * Checks if the time range of two events is overlapping.
     *
     * @param event1 The first event.
     * @param event2 The second event.
     * @return {@code true} if the time ranges overlap, {@code false} otherwise.
     */
    private boolean isTimeRangeOverlapping(Event event1, Event event2) {
        return (event1.getFrom().isBefore(event2.getTo()) || event1.getFrom().isEqual(event2.getTo()))
                && (event1.getTo().isAfter(event2.getFrom()) || event1.getTo().isEqual(event2.getFrom()));
    }
}