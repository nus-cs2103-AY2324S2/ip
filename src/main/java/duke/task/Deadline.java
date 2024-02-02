package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime date;

    public Deadline(String description, LocalDateTime date){
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[D]" + super.toString() + " (by: " + this.date.format(outputFormatter) + ")";
    }
}
