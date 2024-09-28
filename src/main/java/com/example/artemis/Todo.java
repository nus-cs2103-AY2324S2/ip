package com.example.artemis;

/**
 * Represents a todo task in the Artemis application.
 * Extends the abstract Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
        assert description != null && !description.isEmpty() : "Description must not be null or empty";
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task to a string for storage in a file.
     *
     * @return A string representation of the todo task for storage in a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
