package secretaryw;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Task {
    protected TaskType type;
    protected String description;
    protected boolean isDone;

    protected LocalDate deadline;
    protected LocalDate startTime;
    protected LocalDate endTime;

    //constructor for TODO
    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    // Constructor for DEADLINE
    public Task(TaskType type, String description, String deadline) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    //constructor for EVENT
    public Task(TaskType type, String description, String startTime, String endTime) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.startTime = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("d/M/yyyy"));
        this.endTime = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    // Constructor for creating TODO Task from file data
    public Task(TaskType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    // Constructor for creating DEADLINE Task from file data
    public Task(TaskType type, String description, String deadline, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
        this.deadline = LocalDate.parse(deadline);
    }


    // Constructor for creating EVENT Task from file data
    public Task(TaskType type, String description, String startTime, String endTime, boolean isDone) {
        this.type = type;
        this.description = description;
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
        this.isDone = isDone;
    }
    public String getDescription() {
        return this.description;
    }

    public TaskType getType() {
        return this.type;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    // Method to convert a Task to a string for writing to the file
    public String toFileString() {
        switch (type) {
            case TODO:
                return "T | " + (isDone ? "1" : "0") + " | " + description;
            case DEADLINE:
                return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
            case EVENT:
                return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime + " | " + endTime;
            default:
                return "";
        }
    }
    @Override
    public String toString() {
        switch (type) {
            case TODO: //todo
                return "[T]" + getStatusIcon() + " " + description;
            case DEADLINE: // deadline
                String formattedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                return "[D]" + getStatusIcon() + " " + description + " (by: " + formattedDeadline + ")";
            case EVENT: // event
                String formattedStart = startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                String formattedEnd = endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                return "[E]" + getStatusIcon() + " " + description + " (from: " + formattedStart + " to: " + formattedEnd + ")";
            default:
                return ""; // Other types
        }
    }
}

enum TaskType {
    TODO, DEADLINE, EVENT
}