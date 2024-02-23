package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String name, boolean isDone, LocalDateTime by) {
        super(name, isDone);

        this.by = by;
    }

    public LocalDateTime getTime() {
        return this.by;
    }

    @Override
    public String toString(){
        return "[D] " +  super.toString() + " (by:" + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + " | " + getStatusNum() + " | " + this.name + " | " + by.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm")) ;
    }


}