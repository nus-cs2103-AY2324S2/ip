package asher.Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;
    protected LocalTime dueTime;

    public Deadline(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    @Override
    public String writeToString() {
        String status = isDone ? "1" : "0";
        return "D" + " | " + status + " | " + description + " | " + dueDate + " | " + dueTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formattingDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter formattingTime = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedDate = dueDate.format(formattingDate);
        String formattedTime = dueTime.format(formattingTime);
        return " [D]" + super.toString() + " (by: " + formattedDate + "," + " " + formattedTime + ")";
    }
}
