package ezra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected String byInput;
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.byInput = by;
    }

    @Override
    public String toString() {
        String byString = this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, byString);
    }

    public String toString2() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.byInput);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadline d)) {
            return false;
        }
        return this.description.equals(d.description) && this.byInput.equals(d.byInput);
    }
}
