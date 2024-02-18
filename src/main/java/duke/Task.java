package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a task in the task list.
 */
public class Task {
    /**
     * Enumeration representing the types of tasks.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * Indicates whether the task is done or not.
     */
    protected boolean isDone;
    /**
     * The type of the task (TODO, DEADLINE, EVENT).
     */
    protected TaskType taskType;
    /**
     * Constructs a Task object with the given description and task type.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }
    /**
     * Returns an icon representing the status of the task (done or not done).
     *
     * @return The status icon (X if done, blank if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Unmarks the task (marks as not done).
     */
    public void unMark() {
        this.isDone = false;
    }
    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a formatted string for writing the task to a file.
     *
     * @return A formatted string for writing the task to a file.
     */
    public String toFileString() {
        return String.format("%s |  %d | %s", getType(), isDone ? 1 : 0, description);
    }
    /**
     * Creates a Task object from a string representation in a file.
     *
     * @param fileString The string representation of the task in the file.
     * @return A Task object created from the file string.
     */
    public static Task fromFileString(String fileString) {
        String[] elements = fileString.trim().split("\\s*\\|\\s*");
        Boolean done = elements.length > 1 && Integer.parseInt(elements[1]) == 1;
        switch(elements[0]) {
        case "T":
            if (elements.length < 3) {
                throw new IllegalArgumentException("Invalid task format for ToDo in file");
            }
            ToDo todo = new ToDo((elements[2]));
            todo.isDone = done;
            return todo;
        case "D":
            Deadline dline = null;
            if (elements.length >= 4) {
                LocalDate deadlineDate = LocalDate.parse(elements[3], DateTimeFormatter.ISO_DATE);
                dline = new Deadline(elements[2], deadlineDate);
                dline.isDone = done;
            }
            return dline;
        case "E":
            if (elements.length < 5) {
                throw new IllegalArgumentException("Invalid task format for ToDo in file");
            }
            LocalDate fromDate = LocalDate.parse(elements[3], DateTimeFormatter.ofPattern("MMM dd yyyy"));
            LocalDate toDate = LocalDate.parse(elements[4], DateTimeFormatter.ofPattern("MMM dd yyyy"));
            Event event = new Event(elements[2], fromDate, toDate);
            event.isDone = done;
            return event;
        default:
            throw new IllegalArgumentException("Invalid task type in file");
        }

    }
    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return " ";
    }
    public LocalDate getDate() {
        return null;
    }
}

