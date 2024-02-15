package arona;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task that contains a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;
    private DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by.format(targetFormatter) + ")";
    }

    @Override
    public boolean getStatus() {
        return super.getStatus();
    }

    /**
     * Process the string with unmodified date form which is to be saved in the file.
     *
     * @return String that is to be processed and stored in the file.
     */
    @Override
    public String userInputToString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
