package Kokbot.task;

import java.time.LocalDateTime;

/**
 * Represents a To do task
 */
public class Todo extends Task {

    /**
     * Constructor for To do
     *
     * @param description Description of the To do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of the To do
     *
     * @return Type of the To do
     */
    @Override
    public String getType() {
        return "T";
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.MAX;
    }

    /**
     * Represents the To do in String format
     *
     * @return String format of the To do
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Represents the To do in String format for saving to file
     *
     * @return String format of the To do for saving to file
     */
    @Override
    public String toFileString() {
        return String.format("T,%s", super.toFileString());
    }
}
