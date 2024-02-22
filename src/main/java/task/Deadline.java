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

    public Deadline(String name, String byText) throws XiaoBaiException {
        super(name);
        this.name = name;
        this.by = Parser.convertDateTime(byText);
        if (name == null || name.isEmpty()) {
            throw new XiaoBaiException("Task name cannot be empty");
        } else if (by == null) {
            throw new XiaoBaiException("Invalid date format: Deadline dates should be in dd/mm/yyyy HHmm");
        }
    }

    public Deadline(String name, String byText, boolean doneStatus) throws XiaoBaiException {
        super(name, doneStatus);
        this.name = name;
        this.by = Parser.convertDateTime(byText);
        if (name == null || name.isEmpty()) {
            throw new XiaoBaiException("Task name cannot be empty");
        } else if (by == null) {
            throw new XiaoBaiException("Invalid date format: Deadline dates should be in dd/mm/yyyy HHmm");
        }
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), by.format(formatter));
    }
}