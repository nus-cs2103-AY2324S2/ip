package main.java.emis.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime doByDateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
        this.doByDateTime = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
        this.doByDateTime = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String storeString() {
        return "D | " + super.storeString() + " | " + this.by;
    }

    @Override
    public String toString() {
        return"[D]" + super.toString() + " (by: " + this.doByDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
        //this.doByDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
    }
}