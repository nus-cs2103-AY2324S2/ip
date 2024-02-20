package gulie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that has no associated datetimes.
 */
public class Todo extends Task {
    /**
     * A constructor for a Todo.
     * @param name
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * A constructor for a Todo.
     * @param name
     * @param mark
     */
    public Todo(String name, boolean mark) {
        super(name, mark);
    }

    @Override
    public boolean onDate(LocalDate date) {
        return false;
    }

    @Override
    public String toSaveString() {
        return String.format("T\t%s", super.toSaveString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toString(DateTimeFormatter dtf) {
        return toString();
    }
}
