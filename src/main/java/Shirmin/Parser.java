package Shirmin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser for interpreting user input and file data into Tasks.
 * <p>
 * This class is responsible for parsing strings into corresponding Task objects
 * like Todo, Deadline, and Event, and handles parsing of date strings as well.
 */
public class Parser {
    /**
     * Initializes a new Parser instance.
     *
     */
    public Parser() {
    }

    /**
     * Parses a string into a Task object.
     * <p>
     * The method interprets a string representation of a task (e.g., from a saved file)
     * and converts it into a corresponding Task object. The task could be of types Todo,
     * Deadline, or Event. It also handles marking tasks as done if indicated in the input.
     *
     * @param line The string representation of the task to be parsed.
     * @return The parsed Task object, or null if the parsing fails or the task type is unknown.
     */
    public Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        try {
            String type = parts[0];
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) {
                    todo.markDone();
                }
                return todo;
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                String by = parts[3].trim();
                Deadline deadline = new Deadline(description, parseDateString(by));
                if (isDone) {
                    deadline.markDone();
                }
                return deadline;
            case "E":
                if (parts.length < 5) { // missing from/to or both
                    return null;
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                Event event = new Event(description, parseDateString(from), parseDateString(to));
                if (isDone) event.markDone();
                return event;
            default:
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parses a date string into a formatted date string.
     * <p>
     * Attempts to parse a given date string according to a predefined format ("MMM dd yyyy HH:mm")
     * and converts it into a different format ("yyyy-MM-dd HHmm"). If parsing fails, an error
     * message is displayed through the UI component.
     *
     * @param dateString The date string to be parsed.
     * @return The formatted date string, or null if parsing fails.
     * @throws DateTimeParseException if the date string does not match the expected format.
     */
    public String parseDateString(String dateString) throws DateTimeParseException {
        try {
            LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Formats a Task object into a string representation suitable for file storage.
     * <p>
     * The format includes the task type, its completion status, and its description.
     * For Deadline and Event tasks, their respective dates are also included.
     *
     * @param task The Task object to be formatted.
     * @return The formatted string representation of the task.
     */
    public String formatTaskForFile(Task task) {
        String returnString = "";
        String type =
                task instanceof Todo ? "T" :
                        task instanceof Deadline ? "D" :
                                task instanceof Event ? "E" : "";
        int status = task.isDone();
        String details = task.getDescription();
        returnString += type + " | " + status + " | " + details;
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            returnString += " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            returnString += " | " + event.getFrom() + " | " + event.getTo();
        }
        return returnString;
    }
}
