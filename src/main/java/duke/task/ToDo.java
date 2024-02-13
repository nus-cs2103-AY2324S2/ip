package duke.task;

import duke.task.Task;

/**
 * Represents a task that needs to be done.
 */
public class ToDo extends Task {
    public ToDo(String description, int isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }
}
