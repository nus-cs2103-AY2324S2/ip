package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.Duke;
import duke.exceptions.tasks.EmptyDescriptionException;

public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate by;

    public Deadline(String description, LocalDate by) throws EmptyDescriptionException {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) throws EmptyDescriptionException {
        super(description, isDone);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String serialize() {
        ArrayList<String> taskArgs = new ArrayList<>();

        taskArgs.add("deadline");
        taskArgs.add(this.getDescription());
        taskArgs.add(this.getBy().toString());
        taskArgs.add(this.isDone() ? "1" : "0");

        return String.join(Duke.ARG_DELIMITER, taskArgs);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(DATE_TIME_FORMATTER));
    }
}
