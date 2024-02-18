package task;

import java.time.LocalDate;

/**
 * Represents a task in the UncleBob application.
 */
public class Task {
    protected String symbol;
    protected String description;
    protected boolean isDone;
    protected LocalDate by;
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs a Task with the specified symbol and description.
     *
     * @param symbol      the symbol representing the type of the task
     * @param description the description of the task
     */
    public Task(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified symbol, description, and deadline date.
     *
     * @param symbol      the symbol representing the type of the task
     * @param description the description of the task
     * @param by          the deadline date for tasks with a specified deadline
     */
    public Task(String symbol, String description, LocalDate by) {
        this.symbol = symbol;
        this.description = description;
        this.isDone = false;
        this.by = by;
    }

    /**
     * Constructs a Task with the specified symbol, description, start date, and end date.
     *
     * @param symbol      the symbol representing the type of the task
     * @param description the description of the task
     * @param start       the start date for events with a specified duration
     * @param end         the end date for events with a specified duration
     */
    public Task(String symbol, String description, LocalDate start, LocalDate end) {
        this.symbol = symbol;
        this.description = description;
        this.isDone = false;
        this.start = start;
        this.end = end;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getStatus() {
        return isDone ? "1" : "0";
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getBy() {
        return this.by;
    }

    public LocalDate getStart() {
        return this.start;
    }

    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
