package com.example.artemis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    public abstract String toFileString();

    // Convert a string from the file to a task
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid file format: " + fileString);
        }

        String taskType = parts[0].trim();
        boolean isDone = Integer.parseInt(parts[1].trim()) == 1;
        String description = parts[2].trim();

        Task task;
        if (taskType.equals("T")) {
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid file format: " + fileString);
            }
            String by = parts[3].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
            LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);
            task = new Deadline(description, byDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
        } else if (taskType.equals("E")) {
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid file format: " + fileString);
            }
            String from = parts[3].split(" - ")[0].trim();
            String to = parts[3].split(" - ")[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
            LocalDateTime byDateTimeFrom = LocalDateTime.parse(from, formatter);
            LocalDateTime byDateTimeTo = LocalDateTime.parse(to, formatter);
            task = new Event(description, byDateTimeFrom.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")),
                    byDateTimeTo.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
        } else {
            // Handle unknown task type
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}