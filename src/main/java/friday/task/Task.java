package friday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a general task in the Friday application.
 * This is the base class for different types of tasks such as Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Description must not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task's description contains the specified keyword.
     *
     * @param keyword The keyword to search for in the task's description.
     * @return {@code true} if the task's description contains the keyword (case-insensitive), {@code false} otherwise.
     */
    public boolean containsKeyword(String keyword) {
        assert keyword != null : "Keyword must not be null";
        return description.toLowerCase().contains(keyword);
    }

    /**
     * Parses a task from its string representation and returns the corresponding Task object.
     *
     * @param taskString The string representation of the task.
     * @return The parsed Task object.
     */
    public static Task parseTask(String taskString) {
        assert taskString != null : "Task string must not be null";
        String cat = taskString.substring(1, 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        switch (cat) {
        case "T":
            assert taskString.length() >= 7 : "Invalid Todo task string";
            Todo t = new Todo(taskString.substring(7).trim());
            if (taskString.substring(4, 5).equals("X")) {
                t.markAsDone();
            } else {
                t.markAsUndone();
            }
            return t;
        case "D":
            assert taskString.contains("(") && taskString.contains(")") : "Invalid Deadline task string";
            String byString = taskString.split(":")[1].split("\\)")[0].trim();
            LocalDateTime by = LocalDateTime.parse(byString, formatter);
            String description = taskString.split("\\(")[0].substring(7).trim();
            Deadline d = new Deadline(description, by);
            if (taskString.substring(4, 5).equals("X")) {
                d.markAsDone();
            } else {
                d.markAsUndone();
            }
            return d;
        case "E":
            assert taskString.contains("from: ") && taskString.contains("to: ") : "Invalid Event task string";
            int fromIndex = taskString.indexOf("from: ");
            int toIndex = taskString.indexOf("to: ");
            String fromString = taskString.substring(fromIndex + 5, toIndex).trim();
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            String toTimeString = taskString.substring(toIndex + 3).split("\\)")[0].trim();
            LocalDateTime to = LocalDateTime.parse(toTimeString, formatter);
            String des = taskString.split("\\(")[0].substring(7).trim();
            Event e = new Event(des, from, to);
            if (taskString.substring(4, 5).equals("X")) {
                e.markAsDone();
            } else {
                e.markAsUndone();
            }
            return e;
        default:
            System.out.println("Cannot resolve task.");
            return null;
        }
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the Task object.
     *
     * @return The formatted string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
