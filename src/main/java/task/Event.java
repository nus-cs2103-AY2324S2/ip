package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exception.XiaoBaiException;

import parser.Parser;

/**
 * Represents an Event task, which is a type of task that needs to be completed.
 * Inherits from the Task class.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private String name;

    /**
     * Constructs an Event task with the given name, start date, and end date.
     *
     * @param name     The name of the Event task.
     * @param fromText The start date of the event in string format.
     * @param toText   The end date of the event in string format.
     * @throws XiaoBaiException If the task name is empty or null, or if the start
     *                          or end dates are in an invalid format.
     */
    public Event(String name, String fromText, String toText) throws XiaoBaiException {
        super(name);
        this.name = name;
        this.to = Parser.convertDateTime(toText);
        this.from = Parser.convertDateTime(fromText);
        if (name == null || name.isEmpty() || name == " ") {
            throw new XiaoBaiException("Task name cannot be empty");
        } else if (from == null) {
            throw new XiaoBaiException("Invalid from date format: Event dates should be in dd/mm/yyyy HHmm");
        } else if (to == null) {
            throw new XiaoBaiException("Invalid to date format: Event dates should be in dd/mm/yyyy HHmm");
        }
    }

    /**
     * Constructs an Event task with the given name, start date, end date, and
     * completion status.
     *
     * @param name       The name of the Event task.
     * @param fromText   The start date of the event in string format.
     * @param toText     The end date of the event in string format.
     * @param doneStatus The completion status of the task.
     * @throws XiaoBaiException If the task name is empty or null, or if the start
     *                          or end dates are in an invalid format.
     */
    public Event(String name, String fromText, String toText, boolean doneStatus) throws XiaoBaiException {
        super(name, doneStatus);
        this.name = name;
        this.to = Parser.convertDateTime(toText);
        this.from = Parser.convertDateTime(fromText);
        if (name == null || name.isEmpty() || name == " ") {
            throw new XiaoBaiException("Task name cannot be empty");
        } else if (from == null) {
            throw new XiaoBaiException("Invalid from date format: Event dates should be in dd/mm/yyyy HHmm");
        } else if (to == null) {
            throw new XiaoBaiException("Invalid to date format: Event dates should be in dd/mm/yyyy HHmm");
        }
    }

    /**
     * Retrieves the name of the Event task.
     *
     * @return The name of the Event task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the end date of the Event task.
     *
     * @return The end date of the Event task.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Retrieves the start date of the Event task.
     *
     * @return The start date of the Event task.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(formatter), to.format(formatter));
    }
}