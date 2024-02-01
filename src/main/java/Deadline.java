import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    public Deadline(String task, String date) {
        super(task);
        this.date = LocalDate.parse(date);
    }

    public Deadline(String task, String date, boolean status) {
        super(task, status);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String stringify() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.stringify() + "(by: " + formattedDate + ")";
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + this.date;
    }

}
