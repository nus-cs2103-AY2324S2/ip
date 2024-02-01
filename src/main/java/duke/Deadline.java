package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;
    private LocalDate dueDate = null;

    Deadline(String description, LocalDate dueDate, String input) {
        super(description, input);
        this.dueDate = dueDate;
        this.by = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public void mark() {
        super.setComplete();
        System.out.println("\tNice! I've marked this task as done:\n\t" + this.toString());
    }

    @Override
    public void unmark() {
        this.setIncomplete();
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" + this.toString());
    }

    public String toFileFormat() {
        return String.format("Deadline | %s | %s", super.statusNumber, super.input);
    }
}
