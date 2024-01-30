package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dateTime;
    public Deadline(String title, LocalDateTime dateTime) {
        super(title);
        this.dateTime = dateTime;
    }

    @Override
    public String save() {
        if (this.marked) {
            return "D | Done | " + this.title;
        } else {
            return "D | Not Done | " + this.title;
        }
    }
    @Override
    public String toString() {

        if (this.marked) {
            return "[D][X] " + this.title + "by " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return "[D][ ] " + this.title + "by " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }
}
