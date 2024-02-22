
package task;

import parser.ParseExecutionable;

/**
 * Represents a generic Task.
 *
 * This class acts as a base for all specific Task types (e.g., Event, Todo, Deadline).
 * It can be extended to create the corresponding Task.
 */
public abstract class Task implements ParseExecutionable {
    private String command;
    private boolean isCompleted;

    /**
     * Creates a Task object with the task name.
     * Sets the completed to a default value of false.
     *
     * @param command The name of the task.
     */
    public Task(String command) {
        this.command = command;
        this.isCompleted = false;
    }

    /**
     * Returns a string representation of this Task for storage in DataWriter.
     * This includes the formating required for the reader to split and read it.
     *
     * @return a formatted string representation of this object.
     */
    public abstract String formatDataLine();

    /**
     * Changes the Task's completedness to marked.
     *
     * Changes the completedness to true.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Changes the Task's completedness to unmarked.
     *
     * Sets the completedness of the object to false.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Gets the class's completed status.
     */
    public String getCompleted() {
        if (this.isCompleted) {
            return "1";
        }
        return "0";
    }

    /**
     * Gets the class's coommand String.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns a string representation of this Task.
     * This includes the completedness indicator and the Task name.
     *
     * @return a string representation of this Task.
     */
    @Override
    public String toString() {
        String checkIfCompleted = "X";
        if (!this.isCompleted) {
            checkIfCompleted = " ";
        }
        String s = "[" + checkIfCompleted + "] " + this.command;
        return s;
    }
}
