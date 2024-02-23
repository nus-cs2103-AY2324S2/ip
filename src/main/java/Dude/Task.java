package Dude;

// Base Task class

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class representing the structure and functionality of a task.
 * This class serves as a base for different types of tasks that can be created,
 * tracked, and modified.
 * Each task has a description and a status indicating whether it is done.
 */
abstract class Task {
    /**
     * The descriptive text of the task.
     */
    String description;

    /**
     * The completion status of the task.
     */
    boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the isDone field to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting the isDone field to false.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * which includes the description and completion status.
     *
     * @return A string representation of the task.
     */
    @Override
    public abstract String toString();

    public abstract String toFileString();

}

// ToDo subclass
/**
 * Represents a ToDo task with a description.
 * Inherits from the Task class.
 */
class ToDo extends Task {
    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task,
     * including its type, completion status, and description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    @Override
    public String toFileString() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;

    }
}

/**
 * Represents a Deadline task with a description and a due date.
 * Inherits from the Task class.
 */
class Deadline extends Task {
    /**
     * The due date of the Deadline task.
     */
    LocalDate by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     *
     * @param description The description of the Deadline task.
     * @param by          The due date of the task in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
    }

    /**
     * Returns a string representation of the Deadline task,
     * including its type, completion status, description, and due date.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = by.format(formatter);
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " + formattedDate + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String byDate = by.format(formatter);
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + byDate;
    }

    public static Deadline fromCmd(Command cmd) {
        if (!cmd.info.contains(" /by ")) {
            throw new IllegalArgumentException(
                    "Invalid deadline format. Use '/by' to specify the deadline.");
        }
        String[] deadlineParts = cmd.info.split(" /by ", 2);
        return new Deadline(deadlineParts[0], deadlineParts[1]);
    }
}

/**
 * Represents an Event task with a description, start date, and end date.
 * Inherits from the Task class.
 */
class Event extends Task {
    LocalDate start;
    LocalDate end;

    /**
     * Constructs a new Event task with the specified description, start date, and
     * end date.
     *
     * @param description The description of the Event task.
     * @param start       The start date of the event in the format "yyyy-MM-dd".
     * @param end         The end date of the event in the format "yyyy-MM-dd".
     */
    public Event(String description, String start, String end) {
        super(description);
        // Parse the strings to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = LocalDate.parse(start, formatter);
        this.end = LocalDate.parse(end, formatter);
    }

    /**
     * Returns a string representation of the Event task,
     * including its type, completion status, description, start date, and end date.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        // Format the LocalDate to the desired format
        String formattedStart = start.format(formatter);
        String formattedEnd = end.format(formatter);
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " + formattedStart + " to: " + formattedEnd
                + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = start.format(formatter);
        String endDate = end.format(formatter);
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" + startDate + "|" + endDate;
    }

    public static Event fromCmd(Command cmd) {
        if (!cmd.info.contains(" /from ") || !cmd.info.contains(" /to ")) {
            throw new IllegalArgumentException(
                    "Invalid event format. Use '/from' and '/to' to specify the event times.");
        }
        String[] eventParts = cmd.info.split(" /from ", 2);
        String[] eventTimes = eventParts[1].split(" /to ", 2);
        return new Event(eventParts[0], eventTimes[0], eventTimes[1]);
    }
}
