package mona;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }
    @Override
    public String parseToLogRepresentation() {
        int completionStatus = this.isDone ? 1 : 0;
        return "D|" + completionStatus + "|" + this.description + "|" + this.by.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(outputFormatter) + ")";
    }
    @Override
    public void updateDetails(String newDetails) {
        String[] parts = newDetails.split(" /by ");
        this.description = parts[0];
        this.by = LocalDateTime.parse(parts[1], formatter);
    }
}