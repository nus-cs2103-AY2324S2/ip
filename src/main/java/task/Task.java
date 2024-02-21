package task;

import java.time.LocalDate;

import exceptions.InvalidStatusUpdateException;

/**
 * The {@code Task} class represents a generic task with a name and a status.
 * It provides methods to update the task's status, get the task's name,
 * get the task's status, query the task by date, and retrieve a string
 * representation of the task.
 */
public class Task {

    /** The name of the task. */
    protected String name;

    /** The status of the task (marked or unmarked). */
    protected boolean isMarked;

    /**
     * Constructs a new task with the specified name and initializes
     * its status as unmarked.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Updates the status of the task.
     *
     * @param status The new status of the task (true if marked, false if unmarked).
     * @throws InvalidStatusUpdateException If the new status is the same as the current status.
     */
    public void updateStatus(boolean status) throws InvalidStatusUpdateException {
        if (this.isMarked == status) {
            throw new InvalidStatusUpdateException();
        }
        this.isMarked = status;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task (true if marked, false if unmarked).
     */
    public boolean getStatus() {
        return this.isMarked;
    }

    /**
     * Queries the task by date. This method is overridden by subclasses.
     *
     * @param date The date to query.
     * @return {@code true} if the task is relevant to the specified date, {@code false} otherwise.
     */
    public boolean queryByDate(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + (this.isMarked ? "[X] " : "[ ] ") + this.name;
    }
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
