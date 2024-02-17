package floofy.task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getDateString(LocalDate date) {
        return date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + String.format(" | %s", getDateString(this.by));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}