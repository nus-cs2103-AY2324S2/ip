package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDate due;

    public Deadline(String name, LocalDate due) {
        super(name);
        this.due = due;
    }
    @Override
    protected String taskTypeDisplay() {
        return "[D]";
    }
    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s)", this.taskTypeDisplay(), this.completionDisplay(), this.name,
                this.due.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
    @Override
    public String storeFormat() {
        String completeFormat = complete ? "1" : "0";
        return String.format("%s | %s | %s | %s", "D", completeFormat, name, due.format(DateTimeFormatter.ISO_DATE));
    }
}
