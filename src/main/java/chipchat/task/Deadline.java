package chipchat.task;

import chipchat.action.CommandType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate dueBy;

    public Deadline(String description, boolean isDone, LocalDate dueBy) {
        super(description, isDone);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueBy);
    }

    @Override
    public String dataString() {
        return String.format("%s /isDone %s /dueBy %s", CommandType.DEADLINE, super.dataString(), this.dueBy);
    }
}
