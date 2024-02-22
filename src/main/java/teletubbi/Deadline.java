package teletubbi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected String by;
    protected boolean isValidDate;

    /**
     * Creates a new Deadline object with the specified description and deadline.
     *
     * @param description Task description.
     * @param by Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(by);
        if (matcher.matches()) {
            this.isValidDate = true;
            this.byDate = LocalDate.parse(by);
        } else {
            this.isValidDate = false;
            this.by = by;
        }
    }


    /**
     * Returns Deadline task details in the format to be stored in duke.txt.
     *
     * @return Deadline details in file format.
     */
    public String getFileFormat() {
        return isValidDate
                ? "D|" + this.getStatusIcon() + "|" + this.getDescription() + "|" + this.byDate
                : "D|" + this.getStatusIcon() + "|" + this.getDescription() + "|" + this.by;
    }

    @Override
    public String toString() {
        if (isValidDate) {
            return "[D]" + super.toString() + " (by: "
                    + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
