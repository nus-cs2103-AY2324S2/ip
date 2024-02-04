package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Deadline extends Task {

    protected String by;
    protected DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            LocalDateTime date = LocalDateTime.parse(by, input);
            this.by = date.format(output);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format. Please use yyyy-MM-dd HH:mm.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + LocalDateTime.parse(by, output).format(input);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        return this.isDone == deadline.isDone
                && this.description.equals(deadline.description)
                && this.by.equals(deadline.by);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.isDone, this.by);
    }
}
