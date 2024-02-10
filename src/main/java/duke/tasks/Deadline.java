package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    private DateTimeFormatter printFormatter;
    public Deadline(String description, LocalDateTime deadline, DateTimeFormatter printFormatter) {
        super(description);
        this.deadline = deadline;
        this.printFormatter = printFormatter;
    }

    public Deadline(String description, Boolean status, LocalDateTime deadline, DateTimeFormatter printFormatter) {
        super(description, status);
        this.deadline = deadline;
        this.printFormatter = printFormatter;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(this.printFormatter) + ")";
    }

    @Override
    public String convertToFileFormat(DateTimeFormatter storeFormatter) {
        return "D | " + super.convertToFileFormat(storeFormatter) + " | " + this.deadline.format(storeFormatter);
    }

}
