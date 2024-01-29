import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by, boolean isDone) throws SkylerException {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new SkylerException("Invalid date format for deadline. Please use yyyy-MM-dd.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}