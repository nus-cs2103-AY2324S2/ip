package dude.task;

import java.time.LocalDate;

/**
 * Todo task.
 */
public class Todo extends Task {
    /**
     * Creates Todo object.
     * @param description Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates Todo object.
     * @param description Todo description.
     * @param done  Whether todo is done.
     */
    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String getStorageString() {
        return String.format("T | %s", super.getStorageString());
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    };

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
