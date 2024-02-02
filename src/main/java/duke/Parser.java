package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Parser class represents a parser that parses the user input.
 */
public class Parser {

    /**
     * Parses the user input for a "todo" command and adds a new Todo task to the task list.
     * It throws an exception if the input does not contain a description for the todo.
     *
     * @param list The TaskList to which the new todo task is added.
     * @param message The user input string.
     * @throws DukeException If the todo description is empty.
     */
    public static String handleTodo(TaskList list, String message) throws DukeException {
        if (message.trim().equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty buddy.");
        }
        String description = message.substring(5).trim();
        Task task = new Task(description);
        list.add(task);
        // Construct the response message
        String response = "Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + list.size() + " tasks in the list.";
        return response;
    }


    /**
     * Parses and handles the "deadline" command.
     * Adds a deadline task to the task list with a specified due date.
     *
     * @param list The task list to add the deadline to.
     * @param message The user input string.
     * @throws DukeException If the deadline or its date/time format is incorrect.
     */
    public static String handleDeadline(TaskList list, String message) throws DukeException {
        String[] parts = message.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The deadline date/time is missing buddy.");
        }
        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();

        Deadline task;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(by, formatter);
            task = new Deadline(description, date);
            list.add(task);
            Ui.printWithLines("Got it. I've added this task:", task.toString(),
                    "Now you have " + list.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The deadline date format is incorrect. Please use yyyy-MM-dd format.");
        }

        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task.toString(), list.size());
    }

    /**
     * Parses and handles the "event" command.
     * Adds an event task to the task list with specified start and end times.
     *
     * @param list The task list to add the event to.
     * @param message The user input string.
     * @throws DukeException If the event time is missing or the date/time format is incorrect.
     */
    public static String handleEvent(TaskList list, String message) throws DukeException {
        String[] parts = message.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new DukeException("OOPS!!! The event time is missing or incomplete buddy.");
        }
        String description = parts[0].substring(6).trim();
        String[] timeParts = parts[1].split(" /to ", 2);
        String fromTime = timeParts[0].trim();
        String toTime = timeParts[1].trim();
        Event task;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeFrom = LocalDateTime.parse(fromTime, formatter);
            LocalDateTime dateTimeTo = LocalDateTime.parse(toTime, formatter);
            task = new Event(description, dateTimeFrom, dateTimeTo);
            list.add(task);
            Ui.printWithLines("Got it. I've added this task:", task.toString(),
                    "Now you have " + list.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The deadline date format is incorrect. "
                    + "Please use yyyy-MM-dd HH:mm format.");
        }
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task.toString(), list.size());
    }

    /**
     * Handles the "list" command.
     * Displays all tasks in the task list.
     *
     * @param list The task list to display.
     */
    public static String handleList(TaskList list) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            response.append(String.format("%d. %s\n", i + 1, list.get(i).toString()));
        }
        return response.toString().trim();
    }

    /**
     * Parses and handles the "mark" command.
     * Marks a task as done based on the specified task number.
     *
     * @param list The task list containing the task to be marked.
     * @param message The user input string.
     * @throws DukeException If the task number is missing or invalid.
     */
    public static String handleMark(TaskList list, String message) throws DukeException {
        if (message.trim().equals("mark")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(5).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.markAsDone();
        Ui.printWithLines("Nice! I've marked this task as done:", task.toString());
        return String.format("Nice! I've marked this task as done:\n%s", task.toString());
    }

    /**
     * Parses and handles the "unmark" command.
     * Marks a task as not done based on the specified task number.
     *
     * @param list The task list containing the task to be unmarked.
     * @param message The user input string.
     * @throws DukeException If the task number is missing or invalid.
     */
    public static String handleUnmark(TaskList list, String message) throws DukeException {
        if (message.trim().equals("unmark")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(7).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.unMarkAsDone();
        Ui.printWithLines("OK, I've marked this task as not done yet:", task.toString());
        return String.format("OK, I've marked this task as not done yet:\n%s", task.toString());
    }

    /**
     * Parses and handles the "delete" command.
     * Deletes a task from the task list based on the specified task number.
     *
     * @param list The task list from which to delete the task.
     * @param message The user input string.
     * @throws DukeException If the task number is missing or invalid.
     */
    public static String deleteTask(TaskList list, String message) throws DukeException {
        if (message.trim().equals("delete")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(7).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        list.remove(index);
        Ui.printWithLines("OK, I've deleted this task:", task.toString());
        return String.format("OK, I've deleted this task:\n%s", task.toString());
    }

    /**
     * Parses and handles the "find" command.
     * Finds all tasks in the task list that contain the specified keyword.
     *
     * @param list The task list to search for matching tasks.
     * @param message The user input string.
     * @throws DukeException If the keyword is missing.
     */
    public static String findTask(TaskList list, String message) throws DukeException {
        if (message.trim().equals("find")) {
            throw new DukeException("OOPS!!! The keyword is missing buddy.");
        }
        String keyword = message.substring(5).trim();
        ArrayList<Task> matchingTasks = list.find(keyword);
        StringBuilder response = new StringBuilder();
        if (matchingTasks.size() == 0) {
            response.append("No matching task found.");
        } else {
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(String.format("%d. %s\n", i + 1, matchingTasks.get(i).toString()));
            }
        }
        return response.toString().trim();
    }
}
