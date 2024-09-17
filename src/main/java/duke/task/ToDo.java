package duke.task;

import java.time.LocalDate;

/**
 * Subclass of task that represents ToDo tasks.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     *
     * @param description Description of task.
     * @param isDone Boolean value describing completion status
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @inheritDoc
     *
     * @return description + isDone + from + to
     */
    @Override
    public String getTokens() {
        return String.join(",", TaskType.TODO.toString(), super.getTokens());
    }

    /**
     * @inheritDoc
     *
     * @return Smallest date possible as todos do not have a date component
     */
    @Override
    public LocalDate getStartDateTime() {
        return LocalDate.MIN;
    }

}
