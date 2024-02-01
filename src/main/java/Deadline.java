import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    LocalDateTime dueDate;
    public Deadline(String name, String dueDate) {
        super(name, "D");
        String[] dateString = dueDate.split("/");
        int day = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt((dateString[2].split(" "))[0]);
        int time = Integer.parseInt((dateString[2].split(" "))[1]) / 100;
        this.dueDate = LocalDateTime.of(year, month, day, time, 00);
    }
    public String toString() {
        String status = this.complete ? "[x] " : "[ ]";
        DateTimeFormatter returnFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return "[D] " + status + " " + this.name + " (" + returnFormat.format(this.dueDate) + ")";
    }
}
