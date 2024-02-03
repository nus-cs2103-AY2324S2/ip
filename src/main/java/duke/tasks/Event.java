package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.Duke;
import duke.exceptions.tasks.EmptyDescriptionException;

public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) throws EmptyDescriptionException {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDate from, LocalDate to) throws EmptyDescriptionException {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String serialize() {
        ArrayList<String> taskArgs = new ArrayList<>();

        taskArgs.add("event");
        taskArgs.add(this.getDescription());
        taskArgs.add(this.getFrom().toString());
        taskArgs.add(this.getTo().toString());
        taskArgs.add(this.isDone() ? "1" : "0");

        return String.join(Duke.ARG_DELIMITER, taskArgs);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
            this.from.format(DATE_TIME_FORMATTER), this.to.format(DATE_TIME_FORMATTER));
    }
}
