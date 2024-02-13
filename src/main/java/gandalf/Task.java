package gandalf;

import java.io.Serializable;

/**
 * Represents a task with a name and status (completed or not).
 * Implements the Serializable interface to support serialization.
 */
public class Task implements Serializable {
    String nameOfTask;
    boolean status;

    public Task() {

    }
    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
        this.status = false;
    }
    /**
     * Marks the status of the task as completed or not completed.
     *
     * @param status The status of the task (true for completed, false for not completed).
     */
    public void markStatus(boolean status) {
        this.status = status;
    }

    /**
     * Retrieves the status of the task.
     *
     * @return The status of the task (true for completed, false for not completed).
     */

    public boolean getStatus() {
        return this.status;
    }
    public String getNameOfTask() {
        return this.nameOfTask;
    }

    @Override
    public String toString() {
        if(status) {
            return "[X] " + nameOfTask;
        }
        else {
            return "[ ] " + nameOfTask;
        }
    }
}
