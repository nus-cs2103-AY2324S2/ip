package task;

import exception.XiaoBaiException;

/**
 * Represents a task that needs to be done.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a Task with the given name and sets its completion status to
     * false.
     *
     * @param name The name of the task.
     * @throws XiaoBaiException If the task name is empty or null.
     */
    Task(String name) throws XiaoBaiException {
        this.isDone = false;
        this.name = name;
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            throw new XiaoBaiException("Task name cannot be empty");
        }
    }

    /**
     * Constructs a Task with the given name and completion status.
     *
     * @param name   The name of the task.
     * @param isDone The completion status of the task.
     * @throws XiaoBaiException If the task name is empty or null.
     */
    Task(String name, boolean isDone) throws XiaoBaiException {
        this.isDone = isDone;
        this.name = name;
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            throw new XiaoBaiException("Task name cannot be empty");
        }
    }

    /**
     * Marks the task as Done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the status of the task.
     * 
     * @return the status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.name);
        } else {
            return String.format("[  ] %s", this.name);
        }
    }
}