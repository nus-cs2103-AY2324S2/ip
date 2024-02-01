import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{
//    private String by;
    private LocalDate dueDate;
    public Deadline(String description, String by) {
        super(description);
        this.dueDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.dueDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String show() {
        super.status = isDone? "X": " ";
        String dateFormat = this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String byFormat = "(by: " + dateFormat + ")";
        return "[D]" + "[" + status + "]" + " " + this.description + " " + byFormat;
    }
}
