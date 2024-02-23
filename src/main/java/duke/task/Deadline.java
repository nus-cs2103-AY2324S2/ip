package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The task with name and deadline.
 */
public class Deadline extends Task {
    protected String by;
    protected String date;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
        converter(by);
    }

    private void converter(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate inputDate = LocalDate.parse(by, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date = inputDate.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " | by: " + date;
    }
}
