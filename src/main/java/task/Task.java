/**
 * Represents a generic Task.
 * <p>
 * This class acts as a base for all specific Task types (e.g., Event, Todo, Deadline).
 * It can be extended to create the corresponding Task.
 */
package task;

public abstract class Task {
    String command;
    private boolean completed;
    /**
     * Returns a string representation of this Task for storage in DataWriter.
     * This includes the formating required for the reader to split and read it.
     * 
     * @return a formatted string representation of this object. 
     */
    public abstract String formatDataLine();

    /**
     * Creates a Task object with the task name.
     * Sets the completed to a default value of false.
     *
     * @param command The name of the task.
     */
    public Task(String command) {
        this.command = command;
        this.completed = false;
    }

    /**
     * Changes the Task's completedness to marked.
     * <p>
     * Changes the completedness to true.
     * 
     */
    public void mark() {
        this.completed = true;
    }

    /**
     * Changes the Task's completedness to unmarked.
     * <p>
     * Sets the completedness of the object to false.
     * 
     */
    public void unmark() {
        this.completed = false;
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
        if (!this.completed) {
            checkIfCompleted = " ";
        }
        String s = "["+ checkIfCompleted +"] " + this.command;
        return s;
    }
}
