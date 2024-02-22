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

    public Event(String name, String fromText, String toText) throws XiaoBaiException {
        super(name);
        this.name = name;
        this.to = Parser.convertDateTime(toText);
        this.from = Parser.convertDateTime(fromText);
        if (name == null || name.isEmpty()) {
            throw new XiaoBaiException("Task name cannot be empty");
        } else if (from == null) {
            throw new XiaoBaiException("Invalid from date format: Event dates should be in dd/mm/yyyy HHmm");
        } else if (to == null) {
            throw new XiaoBaiException("Invalid to date format: Event dates should be in dd/mm/yyyy HHmm");
        }
    }

    public Event(String name, String fromText, String toText, boolean doneStatus) throws XiaoBaiException {
        super(name, doneStatus);
        this.name = name;
        this.to = Parser.convertDateTime(toText);
        this.from = Parser.convertDateTime(fromText);
        if (name == null || name.isEmpty()) {
            throw new XiaoBaiException("Task name cannot be empty");
        } else if (from == null) {
            throw new XiaoBaiException("Invalid from date format: Event dates should be in dd/mm/yyyy HHmm");
        } else if (to == null) {
            throw new XiaoBaiException("Invalid to date format: Event dates should be in dd/mm/yyyy HHmm");
        }
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(formatter), to.format(formatter));
    }
}