import java.time.LocalDateTime;

public class Deadline extends Task {

    //Legacy support
    protected String by = "";
    protected LocalDateTime byDateTime = null;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        byDateTime = by;
    }

    @Override
    public String saveFile() {
        if (byDateTime == null) {
            return "D" + "|" + super.done() + "|" + super.description + "|" + by + "|" + "null";
        } else {
            return "D" + "|" + super.done() + "|" + super.description + "|" + by + "|" + byDateTime.toString();
        }
    }

    @Override
    public String toString() {
        if (byDateTime == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + DateHandler.formatDate(byDateTime) + ")";
        }
    }
}