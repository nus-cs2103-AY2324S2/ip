package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exception.XiaoBaiException;

import parser.Parser;

/**
 * Represents a Deadline task, which is a type of task that needs to be
 * completed.
 * Inherits from the Task class.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private String name;

    /**
     * Constructs a Deadline task with the given name and deadline.
     *
     * @param name   The name of the Deadline task.
     * @param byText The deadline of the task in string format.
     * @throws XiaoBaiException If the task name is empty or null, or if the
     *                          deadline is in an invalid format.
     */
    public Deadline(String name, String byText) throws XiaoBaiException {
        super(name);
        this.name = name;
        this.by = Parser.convertDateTime(byText);
        if (by == null) {
            throw new XiaoBaiException("Invalid date format: Deadline dates should be in dd/mm/yyyy HHmm");
        }
    }

    /**
     * Constructs a Deadline task with the given name, deadline, and completion
     * status.
     *
     * @param name       The name of the Deadline task.
     * @param byText     The deadline of the task in string format.
     * @param doneStatus The completion status of the task.
     * @throws XiaoBaiException If the task name is empty or null, or if the
     *                          deadline is in an invalid format.
     */
    public Deadline(String name, String byText, boolean doneStatus) throws XiaoBaiException {
        super(name, doneStatus);
        this.name = name;
        this.by = Parser.convertDateTime(byText);
        if (by == null) {
            throw new XiaoBaiException("Invalid date format: Deadline dates should be in dd/mm/yyyy HHmm");
        }
    }

    /**
     * Retrieves the name of the Deadline task.
     *
     * @return The name of the Deadline task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the deadline of the Deadline task.
     *
     * @return The deadline of the Deadline task.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), by.format(formatter));
    }
}