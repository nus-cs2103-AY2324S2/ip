package BartenderBob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{
    private LocalDate dueDate;
    public Deadline(String description, String by) throws IllegalArgumentException {
        super(description);
        if (!isValidDateFormat(by)) {
            throw new IllegalArgumentException();
        }
        this.dueDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public Deadline(String description, String by, boolean isDone) throws IllegalArgumentException {
        super(description, isDone);
        if (!isValidDateFormat(by)) {
            throw new IllegalArgumentException();
        }
        this.dueDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private boolean isValidDateFormat(String by) {
        try {
            LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String show() {
        super.status = isDone? "X": " ";
        String dateFormat = this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String byFormat = "(by: " + dateFormat + ")";
        return "[D]" + "[" + status + "]" + " " + this.description + " " + byFormat;
    }
}
