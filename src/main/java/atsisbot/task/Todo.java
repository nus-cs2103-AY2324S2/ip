package atsisbot.task;

/**
 * Represents a todo atsisbot.task. Inherits from the Task class.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the given description.
     *
     * @param description The description of the todo atsisbot.task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo atsisbot.task.
     *
     * @return A string representation of the todo atsisbot.task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatus().getStatusIcon() + "] " + this.getDescription() + " - "
                + this.priority.toString() + "\n";
    }

    /**
     * Encodes the todo atsisbot.task into a string representation.
     *
     * @return The encoded string representation of the todo atsisbot.task.
     */
    @Override
    public String encode() {
        String status = this.getStatus().isDone() ? "1" : "0";
        return "T | " + status + " | " + this.description + " | " + this.priority.toString();
    }
}
