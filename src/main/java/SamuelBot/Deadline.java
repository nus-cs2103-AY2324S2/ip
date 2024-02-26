package SamuelBot;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDate byDate;
    private String by;

    public Deadline(String description, String by) {
        super(description);
        String[] parts = by.split("\\s+");
        String datePart = parts[0];
        this.byDate = LocalDate.parse(datePart, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public String getBy() {
        return by;
    }

    public String getByDateFormatted() {
        return byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getByDateFormatted() + ")";
    }
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate;
    }

    @Override
    public String getDescription(){
        return description;
    }

    // Abstract method to check if the task is done
    @Override
    public boolean isDone(){
        return isDone;
    }
}