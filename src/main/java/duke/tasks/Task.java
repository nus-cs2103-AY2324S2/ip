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
    private boolean isMarked;

    /**
     * Constructs a Task with the given description.
     * The task is initially isMarked as not completed.
     * @param description A String describing the task.
     */
    Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Checks if the task is isMarked as completed.
     * @return true if the task is isMarked as completed, false otherwise.
     */
    public boolean isMarked() {
        return this.isMarked;
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

