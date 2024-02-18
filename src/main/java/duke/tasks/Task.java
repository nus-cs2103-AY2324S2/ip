package duke.tasks;

import java.time.LocalDate;

/**
 * An abstract class representing a generic Task in a task management application.
 * All specific task types should extend this class.
 * Provides basic functionality for task manipulation such as marking and retrieving deadlines.
 * @author SITHANATHAN RAHUL
 * @version CS2103T AY23/24 Semester 2
 */
public abstract class Task {
    private String description;
    private boolean marked;

    /**
     * Constructs a Task with the given description.
     * The task is initially marked as not completed.
     * @param description A String describing the task.
     */
    Task(String description) {
        this.description = description;
        this.marked = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmark() {
        this.marked = false;
    }

    /**
     * Checks if the task is marked as completed.
     * @return true if the task is marked as completed, false otherwise.
     */
    public boolean isMarked() {
        return this.marked;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a String representation of the task.
     * @return A String describing the task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Returns a String identifier specific to the type of task.
     * @return A String identifier for the task type.
     */
    public abstract String identifier();

    /**
     * Abstract method to be implemented by subclasses.
     * Returns the deadline of the task.
     * @return LocalDate representing the deadline of the task.
     */
    public abstract LocalDate getDeadline();
}

