package gpt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

enum TaskType {
    T, D, E
}

/**
 * Represents  a task.
 */
public class Task {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private boolean isDone;
    private final String taskName;
    private final TaskType taskType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime completedDate;

    /**
     * Constructor for creating a Task instance of taskType T.
     *
     * @param taskName The name of the task.
     * @param taskType The type of the task (T, D, E).
     * @param isDone     The completion status of the task.
     */
    public Task(String taskName, TaskType taskType, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Constructor for creating a Task instance of taskType D.
     *
     * @param taskName The name of the task.
     * @param taskType The type of the task (T, D, E).
     * @param isDone     The completion status of the task.
     * @param startDate The start date of the task.
     */
    public Task(String taskName, TaskType taskType, Boolean isDone, String startDate) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.taskType = taskType;
        this.startDate = parseDateTime(startDate);
    }

    /**
     * Constructor for creating a Task instance of taskType E.
     *
     * @param taskName The name of the task.
     * @param taskType The type of the task (T, D, E).
     * @param isDone     The completion status of the task.
     * @param startDate The start date of the task.
     * @param endDate The end date of the task.
     */
    public Task(String taskName, TaskType taskType, Boolean isDone, String startDate, String endDate) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.taskType = taskType;
        this.startDate = parseDateTime(startDate);
        this.endDate = parseDateTime(endDate);
    }

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
        this.completedDate = LocalDateTime.now();
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() {
        this.isDone = false;
        this.completedDate = null;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return The completion status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Returns the end date of the task.
     *
     * @return The end date of the task.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Returns the start date of the task.
     *
     * @return The start date of the task.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Returns the start date of the task as a string.
     *
     * @return The start date of the task as a string.
     */
    public String getStartDateString() {
        if (startDate != null) {
            return startDate.format(DATE_TIME_FORMAT);
        } else {
            return null;
        }

    }

    /**
     * Returns the end date of the task as a string.
     *
     * @return The end date of the task as a string.
     */
    public String getEndDateString() {
        if (endDate != null) {
            return endDate.format(DATE_TIME_FORMAT);
        } else {
            return null;
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        switch (taskType) {
        case T:
            return String.format("[%s]%s %s", taskType, status, taskName);
        case D:
            return String.format("[%s]%s %s (by: %s)", taskType, status, taskName,
                    startDate.format(DATE_FORMAT_OUTPUT));
        case E:
            return String.format("[%s]%s %s (from: %s to: %s)", taskType, status, taskName,
                    startDate.format(DATE_FORMAT_OUTPUT), endDate.format(DATE_FORMAT_OUTPUT));
        default:
            return "";
        }
    }

    /**
     * Parses a date and time string into a LocalDateTime object.
     *
     * @param dateTimeString The date and time string to be parsed.
     * @return The LocalDateTime object parsed from the date and time string.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        assert dateTimeString != null : "DateTime string should not be null";
        try {
            return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMAT);
        } catch (Exception e) {
            // Handle parsing errors, e.g., invalid date format
            System.out.println("Error parsing date: " + e.getMessage());
            return null; // Or throw an exception if appropriate
        }
    }
}
