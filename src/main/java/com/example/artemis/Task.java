package com.example.artemis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the Artemis application.
 * This is an abstract class extended by specific task types (Todo, Deadline, Event).
 */
public abstract class Task {
    public static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy h:mma";
    public static final String INPUT_DATE_FORMAT = "dd-MM-yyyy HHmm";
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Converts the task to a string for storage in a file.
     *
     * @return A string representation of the task for storage in a file.
     */
    public abstract String toFileString();

    /**
     * Converts a string from the file to a Task object.
     *
     * @param fileString The string representation of the task in the file.
     * @return A Task object created from the file string.
     * @throws IllegalArgumentException If the file string has an invalid format.
     */
    public static Task fromFileString(String fileString) {
        assert fileString != null : "File string cannot be null";

        String[] parts = fileString.split("\\|");

        assert parts.length >= 3 && parts.length <= 4 : "Invalid file format: " + fileString;

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid file format: " + fileString);
        }

        String taskType = parts[0].trim();

        assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E") : "Unknown task type: " + taskType;

        boolean isDone = Integer.parseInt(parts[1].trim()) == 1;
        String description = parts[2].trim();

        Task task;
        switch (taskType) {
        case "T":
            task = getToDoTask(description);
            break;
        case "D":
            task = getDeadlineTask(fileString, parts, description);
            break;
        case "E":
            task = getEventTask(fileString, parts, description);
            break;
        default:
            // Handle unknown task type
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Converts a string from the file to a Task object representing an event task.
     *
     * @param fileString The string representation of the task in the file.
     * @param parts The parts obtained after splitting the file string.
     * @param description The description of the task.
     * @return An Event task object created from the file string.
     * @throws IllegalArgumentException If the file string has an invalid format.
     */
    private static Task getEventTask(String fileString, String[] parts, String description) {
        Task task;
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid file format: " + fileString);
        }

        String from = parts[3].split(" - ")[0].trim();
        String to = parts[3].split(" - ")[1].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        LocalDateTime byDateTimeFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime byDateTimeTo = LocalDateTime.parse(to, formatter);

        task = new Event(description, byDateTimeFrom
                .format(DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)),
                byDateTimeTo.format(DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));

        return task;
    }

    /**
     * Converts a string from the file to a Task object representing a deadline task.
     *
     * @param fileString The string representation of the task in the file.
     * @param parts The parts obtained after splitting the file string.
     * @param description The description of the task.
     * @return A Deadline task object created from the file string.
     * @throws IllegalArgumentException If the file string has an invalid format.
     */
    private static Task getDeadlineTask(String fileString, String[] parts, String description) {
        Task task;
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid file format: " + fileString);
        }

        String by = parts[3].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);

        task = new Deadline(description, byDateTime
                .format(DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));

        return task;
    }

    /**
     * Converts a string from the file to a Task object representing a todo task.
     *
     * @param description The description of the task.
     * @return A Todo task object created from the description.
     */
    private static Task getToDoTask(String description) {
        Task task;
        task = new Todo(description);
        return task;
    }
}
